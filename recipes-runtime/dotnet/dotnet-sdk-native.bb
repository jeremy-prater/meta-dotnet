
DESCRIPTION = ".NET Core SDK (v8.0.303) - Linux x64 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/6.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "dotnet-sdk-8.0.303-linux-x64.tar.gz"

SRC_URI = "https://download.visualstudio.microsoft.com/download/pr/60218cc4-13eb-41d5-aa0b-5fd5a3fb03b8/6c42bee7c3651b1317b709a27a741362/${SOURCE_FILE};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha256sum] = "5d4a58eeca1a4ab095b55e6486b1278147b0ab121ce883b2bcde3d3626c9a81c"

inherit native

S="${WORKDIR}"

do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}${bindir}
    tar -xvzf ${WORKDIR}/${SOURCE_FILE} -C ${D}${bindir}
}
