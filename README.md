# meta-dotnet
Yocto meta-layer for dotnet core 8.0.100 for armv7/aarch64/x86

# Compatibility

| Branch     | Compatible Layers | Supported Arch         | dotnet version |
|------------|-------------------|------------------------|----------------|
| master     | Kirkstone         | x86_64, armv7, aarch64 | 8.0.100        |
| kirkstone  | Kirkstone         | x86_64, armv7, aarch64 | 8.0.100        |
| dunfell    | dunfell, zeus     | x86_64, armv7, aarch64 | 7.0.100        |
| pyro       | N/A               | x86_64, armv7          | 3.1.101        |

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
- Performs the required build steps `dotnet restore` and `dotnet build -c Release ...`

# Deployment

The resultant application is a self-contained, compressed, trimmed package. No dotnet runtime is required on the target rootfs

Installation path and artifacts path can be configured from the package recipe

* `INSTALL_DIR` can be used to change the default `/opt/dotnet/${PN}` installation directory
