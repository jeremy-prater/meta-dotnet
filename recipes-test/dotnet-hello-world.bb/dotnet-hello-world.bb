DESCRIPTION = "dotnet test application"
LICENSE = "CLOSED"

SRC_URI = "file://hello-world/hello-world.cs \
           file://hello-world/hello-world.csproj \
           file://hello-world.sln \
"

DOTNET_PROJECT = "hello-world"

S = "${UNPACKDIR}"

inherit dotnet
