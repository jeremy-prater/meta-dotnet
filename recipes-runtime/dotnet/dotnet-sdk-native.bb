DESCRIPTION = ".NET SDK (v${PV}) - Linux x64 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/10.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

PV = "10.0.401"

SOURCE_FILE = "dotnet-sdk-${PV}-linux-x64.tar.gz"

SRC_URI = "https://builds.dotnet.microsoft.com/dotnet/Sdk/${PV}/${SOURCE_FILE};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha512sum] = "51c8b999af9e8dd9998c9edc5944e19a90788862068acd38694e098889054ce8c23d4f0c5cccfa16bf187d044562359e5ee69a9f8ad0bbe913ba90311fbce25b"

inherit native

S = "${UNPACKDIR}"

do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}${bindir}
    tar -axf ${UNPACKDIR}/${SOURCE_FILE} -C ${D}${bindir}
}

INSANE_SKIP:${PN} += "already-stripped"
