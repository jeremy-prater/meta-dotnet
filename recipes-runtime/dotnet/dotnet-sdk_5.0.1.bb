
DESCRIPTION = ".NET Core 5.1 SDK (v5.0.101) - Linux ARM32 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/download/dotnet-core/3.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "dotnet-sdk-5.0.101-linux-arm.tar.gz"
SOURCE_URL = "https://download.visualstudio.microsoft.com/download/pr/567a64a8-810b-4c3f-85e3-bc9f9e06311b/02664afe4f3992a4d558ed066d906745/${SOURCE_FILE}"

SRC_URI = "${SOURCE_URL};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha256sum] = "65d51e333ea2b9ede58a805f488b0ceb634a10d52dff628f479cc5fae102acec"

RCONFLICTS_${PN} += "dotnet"

include dotnet_installer.inc
