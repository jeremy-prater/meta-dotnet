# Path to the dotnet project
OECMAKE_SOURCEPATH ??= "${S}"

DEPENDS_prepend += "dotnet-sdk-native "

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

dotnet_do_compile()  {
    # Don't use users's $HOME/.dotnet during compilation
    export HOME="${WORKDIR}"
    echo "Building project ${DOTNET_PROJECT}"

    if [ "${TARGET_ARCH}" = "x86_64" ]; then
        BUILD_TARGET="linux-x64"
    elif [ "${TARGET_ARCH}" = "aarch64" ]; then
	BUILD_TARGET="linux-arm64"
    else
        BUILD_TARGET="linux-arm"
    fi

    echo "Machine Type ${MACHINE} -> Build Target ${BUILD_TARGET}"

    cd ${S}
    mkdir -p ${B}
    dotnet publish -c Release -p:PublishTrimmed=true -o ${B} -r ${BUILD_TARGET} --self-contained true ${DOTNET_PROJECT}
}

dotnet_do_install() {
    cd ${B}
    rm -rf recipe-sysroot-native
    mkdir -p ${D}/opt/dotnet/${PN}
    cp -rv * ${D}/opt/dotnet/${PN}
}

INSANE_SKIP_${PN}_append += "already-stripped"
INSANE_SKIP_${PN}_append += "staticdev"
INSANE_SKIP_${PN}_append += "file-rdeps"

FILES_${PN} += "/opt/dotnet/${PN}"

EXPORT_FUNCTIONS do_configure do_compile do_install
