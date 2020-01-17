
DESCRIPTION = ".NET Core 3.1 SDK (v3.1.101) - Linux ARM32 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/download/dotnet-core/3.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "dotnet-sdk-3.1.101-linux-arm.tar.gz"
SOURCE_URL = "https://download.visualstudio.microsoft.com/download/pr/d52fa156-1555-41d5-a5eb-234305fbd470/173cddb039d613c8f007c9f74371f8bb/${SOURCE_FILE}"

SRC_URI = "${SOURCE_URL};unpack=0 \
           file://LICENSE.txt \
"
SRC_URI[sha256sum] = "65d51e333ea2b9ede58a805f488b0ceb634a10d52dff628f479cc5fae102acec"

RCONFLICTS_${PN} += "dotnet"

include dotnet_installer.inc
