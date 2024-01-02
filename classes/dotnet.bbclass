# Path to the dotnet project
OECMAKE_SOURCEPATH ??= "${S}"

DEPENDS:prepend = "dotnet-sdk-native "

ARTIFACTS_DIR = "${WORKDIR}/artifacts"
RELEASE_DIR ?= "${ARTIFACTS_DIR}/publish/${DOTNET_PROJECT}/release_linux-arm/"

INSTALL_DIR ?= "/opt/dotnet/${PN}"

ENABLE_READYTORUN ?= "true"

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

    if [ "${TARGET_ARCH}" = "x86_64" ]; then
        BUILD_TARGET="linux-x64"
    elif [ "${TARGET_ARCH}" = "aarch64" ]; then
        BUILD_TARGET="linux-arm64"
    elif [ "${TARGET_ARCH}" = "arm" ]; then
        BUILD_TARGET="linux-arm"
    else
        bberror "Architecture not supported: ${TARGET_ARCH}"
        exit -1
    fi

    cd ${S}
    ${STAGING_DIR_NATIVE}${bindir}/dotnet publish \
        --artifacts-path ${ARTIFACTS_DIR} \
        --runtime ${BUILD_TARGET} \
        --self-contained true \
        -p:PublishTrimmed=true \
        -p:PublishReadyToRun=${ENABLE_READYTORUN} \
        -c Release \
        ${DOTNET_PROJECT}
}

dotnet_do_install() {
    install -d ${D}/${INSTALL_DIR}
    cp -r ${RELEASE_DIR}/* ${D}/${INSTALL_DIR}
}

INSANE_SKIP:${PN}:append = " \
    staticdev \
    file-rdeps \
    already-stripped \
"

do_configure[network] = "1"
do_compile[network] = "1"

FILES:${PN} = "${INSTALL_DIR}"

EXPORT_FUNCTIONS do_configure do_compile do_install
