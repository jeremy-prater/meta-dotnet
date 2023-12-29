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

B = "${S}/out"

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
    mkdir -p ${B}
    dotnet publish -c Release -p:PublishTrimmed=true -o ${B} -r ${BUILD_TARGET} --self-contained true -p:PublishSingleFile=true -p:EnableCompressionInSingleFile=true ${DOTNET_PROJECT}
}

dotnet_do_install() {
    cd ${B}
    rm -rf recipe-sysroot-native
    mkdir -p ${D}/opt/dotnet/${PN}
    cp -rv * ${D}/opt/dotnet/${PN}
}

INSANE_SKIP:${PN}:append = " \
    staticdev \
    file-rdeps \
    already-stripped \
"

do_configure[network] = "1"
do_compile[network] = "1"

FILES:${PN} = "/opt/dotnet/${PN}"

EXPORT_FUNCTIONS do_configure do_compile do_install
