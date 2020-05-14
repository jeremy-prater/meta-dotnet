
DESCRIPTION = "ASP.NET Core 3.1 Runtime (v3.1.1) - Linux ARM32 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/download/dotnet-core/3.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "aspnetcore-runtime-3.1.1-linux-arm.tar.gz"
SOURCE_URL = "https://download.visualstudio.microsoft.com/download/pr/da60c9fc-c329-42d6-afaf-b8ef2bbadcf3/14655b5928319349e78da3327874592a/${SOURCE_FILE}"

SRC_URI = "${SOURCE_URL};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha256sum] = "78948786fb2893a78caf6662b1832877064c139006af37d79257017880372d9b"

RCONFLICTS_${PN} += "dotnet-sdk"

include dotnet_installer.inc
