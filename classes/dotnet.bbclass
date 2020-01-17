# Path to the dotnet project
OECMAKE_SOURCEPATH ??= "${S}"

DEPENDS_prepend += "dotnet-sdk-native "
RDEPENDS_${PN} += "dotnet "

B = "${WORKDIR}/out"

dotnet_do_configure() {
    cd ${WORKDIR}
    dotnet restore
}

dotnet_do_compile()  {
	cd ${WORKDIR}
    mkdir -p ${B}
    dotnet build -o ${B} -c Release
}

dotnet_do_install() {
	cd ${B}
    rm -rf recipe-sysroot-native
    mkdir -p ${D}/opt/dotnet/${PN}
    cp -rv * ${D}/opt/dotnet/${PN}
}

FILES_${PN} += "/opt/dotnet/${PN}"

EXPORT_FUNCTIONS do_configure do_compile do_install
