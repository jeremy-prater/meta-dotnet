# meta-dotnet
Yocto meta-layer for dotnet core 5.1 for armv7

# Usage

Add this meta layer to your project (refer to yocto user manual)

Create a new dotnet core application and include it in your yocto build as follows...

```
DESCRIPTION = "My dot net core 3.1 app"
LICENSE = "CLOSED"

SRC_URI = "file://hello-world.cs \
           file://hello-world.csproj \
"

inherit dotnet
```

This does a few things, when you `inherit dotnet` meta-layer class, it will does the following...

- Automatically download the host dotnet sdk for linux x64 as a native build tool `dotnet-sdk-native` (In the future this could become mac and windows compatible, but I only yocto on linux)
- Add an RDEPENDS_${PN} for `dotnet` which is the arm v7 runtime
- Performs the required build steps `dotnet restore` and `dotnet build -c Release`

# Deployment

The dotnet 3.1 runtime gets installed into `/opt/dotnet`

And user applications get installed at `/opt/dotnet/${PN}`

Running the application on target should be trivial by adding `/opt/dotnet` to the environment path so the `dotnet` runtime interpreter is available.

Then you can just `dotnet mydotnetapp.dll` assuming your dotnet application library has an entry point!