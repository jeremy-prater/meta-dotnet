
DESCRIPTION = ".NET Core 5.0 SDK (v5.0.101) - Linux x64 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/download/dotnet/5.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "dotnet-sdk-5.0.101-linux-x64.tar.gz"

SRC_URI = "https://download.visualstudio.microsoft.com/download/pr/a0487784-534a-4912-a4dd-017382083865/be16057043a8f7b6f08c902dc48dd677/${SOURCE_FILE};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha256sum] = "23df1eca7eb1302dfb10f4edce7edf7150e57698576f61b2dcb777c833cbd80c"

inherit native

S="${WORKDIR}"

do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}${bindir}
    tar -xvzf ${WORKDIR}/${SOURCE_FILE} -C ${D}${bindir}
}
