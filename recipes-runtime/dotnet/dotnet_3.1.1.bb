
DESCRIPTION = "ASP.NET Core 3.1 Runtime (v3.1.1) - Linux x86_64 / ARM32 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/download/dotnet-core/3.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "${@oe.utils.conditional("TARGET_ARCH", "x86_64", \
        "aspnetcore-runtime-3.1.7-linux-x64.tar.gz", \
        "aspnetcore-runtime-3.1.7-linux-arm.tar.gz", d)}"

SOURCE_URL = "${@oe.utils.conditional("TARGET_ARCH", "x86_64", \
        "https://download.visualstudio.microsoft.com/download/pr/e7d0601d-41b4-483f-b411-f2b42708054a/191b56b81e1830b413d0794728831eea/${SOURCE_FILE}", \
        "https://download.visualstudio.microsoft.com/download/pr/5ed60e45-f93a-4a8b-ab92-4034fcf00618/cf2aafe9bc91f28bd4d7b7436c31e27e/${SOURCE_FILE}", d)}"

SOURCE_CHECKSUM = "${@oe.utils.conditional("TARGET_ARCH", "x86_64", \
        "4f0ce619c1b1dbc8ccd799877b5d73158a07b1ebd1222d44b909bba13bdf735c", \
        "184250cefa4ba443d24445d237c19e9096d1f1537cf3d17438b9bdf796c0a5d2", d)}"

SRC_URI = "${SOURCE_URL};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha256sum] = "${SOURCE_CHECKSUM}"

RCONFLICTS_${PN} += "dotnet-sdk"

include dotnet_installer.inc
