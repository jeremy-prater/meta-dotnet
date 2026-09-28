# Common definitions for the .NET SDK native (host) recipes.
#
# Each versioned dotnet-sdk-native_<ver>.bb recipe requires this file and
# provides the SRC_URI[sha512sum] for its tarball. The version to build is
# selected through DOTNET_VERSION (see conf/layer.conf), which maps to
# PREFERRED_VERSION_dotnet-sdk-native.

DOTNET_MAJOR_MINOR = "${@'.'.join(d.getVar('PV').split('.')[:2])}"

# Full upstream SDK version string; defaults to PV. Pre-release recipes
# (e.g. release candidates) override this with the complete build number.
DOTNET_SDK_VERSION ?= "${PV}"

DESCRIPTION = ".NET SDK (v${DOTNET_SDK_VERSION}) - Linux x64 Binaries"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/${DOTNET_MAJOR_MINOR}"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

SOURCE_FILE = "dotnet-sdk-${DOTNET_SDK_VERSION}-linux-x64.tar.gz"

SRC_URI = "https://builds.dotnet.microsoft.com/dotnet/Sdk/${DOTNET_SDK_VERSION}/${SOURCE_FILE};unpack=0 \
           file://LICENSE.txt \
"

inherit native

# Newer releases (styhead and later) unpack into UNPACKDIR rather than WORKDIR,
# and whinlatter/wrynose reject S = ${WORKDIR} outright. Older releases
# (kirkstone/scarthgap) do not define UNPACKDIR at all, so fall back to WORKDIR
# there. UNPACKDIR itself must not be overridden (a recipe-level ?= would beat
# the ??= default in bitbake.conf and trip the same QA check).
S = "${@d.getVar('UNPACKDIR') or d.getVar('WORKDIR')}"

do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}${bindir}
    tar -axf ${S}/${SOURCE_FILE} -C ${D}${bindir}
}

INSANE_SKIP:${PN} += "already-stripped"
