DESCRIPTION = "dotnet 5.0 test application"
LICENSE = "CLOSED"

SRC_URI = "file://hello-world/hello-world.cs \
           file://hello-world/hello-world.csproj \
           file://hello-world.sln \
"

DOTNET_PROJECT = "hello-world"

S = "${WORKDIR}"

inherit dotnet
