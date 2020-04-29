# Path to the dotnet project
OECMAKE_SOURCEPATH ??= "${S}"

DEPENDS_prepend += "dotnet-sdk-native "
RDEPENDS_${PN} += "dotnet "

B = "${S}/out"

dotnet_do_configure() {
    cd ${S}
    dotnet restore
}

dotnet_do_compile()  {
    echo "Building project ${DOTNET_PROJECT}"
    cd ${S}
    mkdir -p ${B}
    dotnet publish ${DOTNET_PROJECT} -o ${B} -c Release --no-self-contained -r linux-arm
}

dotnet_do_install() {
    cd ${B}
    rm -rf recipe-sysroot-native
    mkdir -p ${D}/opt/dotnet/${PN}
    cp -rv * ${D}/opt/dotnet/${PN}
}

FILES_${PN} += "/opt/dotnet/${PN}"

EXPORT_FUNCTIONS do_configure do_compile do_install
