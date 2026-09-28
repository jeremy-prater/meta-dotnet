DESCRIPTION = ".NET test application (targets the framework bundled with the selected SDK)"
LICENSE = "CLOSED"

SRC_URI = "file://hello-world/hello-world.cs \
           file://hello-world/hello-world.csproj \
           file://hello-world.sln \
"

DOTNET_PROJECT = "hello-world"

# Newer releases (styhead and later) unpack into UNPACKDIR rather than WORKDIR;
# older releases (kirkstone/scarthgap) do not define UNPACKDIR.
S = "${@d.getVar('UNPACKDIR') or d.getVar('WORKDIR')}"

inherit dotnet
