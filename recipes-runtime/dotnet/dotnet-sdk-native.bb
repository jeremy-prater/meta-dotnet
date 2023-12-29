
DESCRIPTION = ".NET Core SDK (v8.0.100) - Linux x64 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/6.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "dotnet-sdk-8.0.100-linux-x64.tar.gz"

SRC_URI = "https://download.visualstudio.microsoft.com/download/pr/5226a5fa-8c0b-474f-b79a-8984ad7c5beb/3113ccbf789c9fd29972835f0f334b7a/${SOURCE_FILE};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha512sum] = "13905ea20191e70baeba50b0e9bbe5f752a7c34587878ee104744f9fb453bfe439994d38969722bdae7f60ee047d75dda8636f3ab62659450e9cd4024f38b2a5"

inherit native

S="${WORKDIR}"

do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}${bindir}
    tar -axf ${WORKDIR}/${SOURCE_FILE} -C ${D}${bindir}
}
