
DESCRIPTION = ".NET Core 3.1 SDK (v3.1.101) - Linux x64 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/download/dotnet-core/3.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "dotnet-sdk-3.1.101-linux-x64.tar.gz"

SRC_URI = "https://download.visualstudio.microsoft.com/download/pr/c4b503d6-2f41-4908-b634-270a0a1dcfca/c5a20e42868a48a2cd1ae27cf038044c/${SOURCE_FILE};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha256sum] = "a1060891482267f4b36a877b547396d7838bc36c65ef16db192344fd9b29211d"

inherit native

S="${WORKDIR}"

do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}${bindir}
    tar -xvzf ${WORKDIR}/${SOURCE_FILE} -C ${D}${bindir}
}
