# Path to the dotnet project
OECMAKE_SOURCEPATH ??= "${S}"

DEPENDS:prepend = "dotnet-sdk-native "

python () {
    target_arch = d.getVar("TARGET_ARCH")
    if "x86_64" in target_arch:
        d.appendVar('BUILD_TARGET', "linux-x64")
        return
    if "aarch64" in target_arch:
        d.appendVar('BUILD_TARGET', "linux-arm64")
        return
    if "arm" in target_arch:
        d.appendVar('BUILD_TARGET', "linux-arm")
        return

    bb.fatal("Architecture not supported: " + target_arch)
}

ARTIFACTS_DIR = "${WORKDIR}/artifacts"
RELEASE_DIR ?= "${ARTIFACTS_DIR}/publish/${DOTNET_PROJECT}/release_${BUILD_TARGET}/"

INSTALL_DIR ?= "/opt/dotnet/${PN}"

ENABLE_READYTORUN ?= "false"
ENABLE_TRIMMING ?= "true"

dotnet_do_configure() {
    # Don't use users's $HOME/.dotnet during configuration
    export HOME="${WORKDIR}"
    if [ -z ${DOTNET_PROJECT} ] ; then
     bberror "DOTNET_PROJECT must be specified!"
     exit -1
    fi
    cd ${S}
    dotnet restore
}

dotnet_do_compile() {
    export HOME="${WORKDIR}"
    echo "Building project ${DOTNET_PROJECT}"

    cd ${S}
    ${STAGING_DIR_NATIVE}${bindir}/dotnet publish \
        --artifacts-path ${ARTIFACTS_DIR} \
        --runtime ${BUILD_TARGET} \
        --self-contained true \
        -p:PublishTrimmed=${ENABLE_TRIMMING} \
        -p:PublishReadyToRun=${ENABLE_READYTORUN} \
        -c Release \
        ${DOTNET_PROJECT}
}

dotnet_do_install() {
    install -d ${D}/${INSTALL_DIR}
    cp -R --no-dereference --preserve=mode,links -v ${RELEASE_DIR}/* ${D}/${INSTALL_DIR}/
}

INSANE_SKIP:${PN}:append = " \
    staticdev \
    file-rdeps \
    already-stripped \
"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[network] = "1"
do_compile[network] = "1"

FILES:${PN} = "${INSTALL_DIR}"

EXPORT_FUNCTIONS do_configure do_compile do_install
