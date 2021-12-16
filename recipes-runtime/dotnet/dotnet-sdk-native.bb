
DESCRIPTION = ".NET Core 5.0 SDK (v6.0.1) - Linux x64 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/6.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "dotnet-sdk-6.0.101-linux-x64.tar.gz"

SRC_URI = "https://download.visualstudio.microsoft.com/download/pr/ede8a287-3d61-4988-a356-32ff9129079e/bdb47b6b510ed0c4f0b132f7f4ad9d5a/${SOURCE_FILE};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha256sum] = "95a1b5360b234e926f12327d68c4a0d7b7206134dca1b570a66dc7a8a4aed705"

inherit native

S="${WORKDIR}"

do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}${bindir}
    tar -xvzf ${WORKDIR}/${SOURCE_FILE} -C ${D}${bindir}
}
