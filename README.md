# meta-dotnet
Yocto meta-layer for .NET 8 / 9 / 10 / 11 for armv7/aarch64/x86_64

# Compatibility

| Branch     | Compatible Layers                                                | Supported Arch         | dotnet version                                    |
|------------|------------------------------------------------------------------|------------------------|---------------------------------------------------|
| trunk      | Kirkstone, Scarthgap, Styhead, Walnascar, Whinlatter, Wrynose    | x86_64, armv7, aarch64 | 8.0.425, 9.0.318, 10.0.401 (default), 11.0 RC1    |
| kirkstone  | Kirkstone                                                        | x86_64, armv7, aarch64 | 8.0.303                                           |
| dunfell    | dunfell, zeus                                                    | x86_64, armv7, aarch64 | 8.0.303                                           |
| pyro       | N/A                                                              | x86_64, armv7          | 3.1.101                                           |

# Selecting the .NET version

The layer ships one `dotnet-sdk-native` recipe per .NET major version. Which one
is used is controlled by `DOTNET_VERSION` (default `10`, the current LTS release).
Set it in your `local.conf` or distro configuration:

```
DOTNET_VERSION = "9"
```

| DOTNET_VERSION | SDK                       | Support                                        |
|----------------|---------------------------|------------------------------------------------|
| `8`            | 8.0.425                   | LTS, end of support November 10, 2026          |
| `9`            | 9.0.318                   | STS, end of support November 10, 2026          |
| `10` (default) | 10.0.401                  | LTS, end of support November 14, 2028          |
| `11`           | 11.0.100-rc.1.26425.128   | Release candidate (go-live), not yet GA        |

`DOTNET_VERSION` maps to `PREFERRED_VERSION_dotnet-sdk-native` in
`conf/layer.conf`. If you need finer control (for example pinning an exact SDK
build), set `PREFERRED_VERSION_dotnet-sdk-native = "9.0.318"` directly instead.

Recipes inheriting the `dotnet` class also get `DOTNET_TARGET_FRAMEWORK`
(`net${DOTNET_VERSION}.0`), which can be passed to your project if needed.
Projects that want to follow the selected SDK automatically can use
`<TargetFramework>net$(BundledNETCoreAppTargetFrameworkVersion)</TargetFramework>`
in their `.csproj`, as the bundled `dotnet-hello-world` test recipe does.

# Usage

Add this meta layer to your project (refer to yocto user manual)

You may need to ~add following lines to you local.conf file~ (enabling access to NuGet.org in configure and compile steps):  

You may not need this in `local.conf` it could just be in the recipe for your project in the bb recipe...

```
do_configure[network] = "1"
do_compile[network] = "1"
```

Create a new dotnet core application and include it in your yocto build as follows...

```
DESCRIPTION = "My .NET app"
LICENSE = "CLOSED"

SRC_URI = "file://hello-world.cs \
           file://hello-world.csproj \
"

DOTNET_PROJECT = "hello-world"

S = "${@d.getVar('UNPACKDIR') or d.getVar('WORKDIR')}"

inherit dotnet
```

Note: from Styhead onwards sources are unpacked into `${UNPACKDIR}` rather than
`${WORKDIR}`, and Whinlatter/Wrynose reject `S = "${WORKDIR}"`. The `S` line
above uses `UNPACKDIR` when it exists and falls back to `WORKDIR` on older
releases (Kirkstone/Scarthgap). Do not set `UNPACKDIR` yourself.

This does a few things, when you `inherit dotnet` meta-layer class, it will does the following...

- Automatically download the host dotnet sdk for linux x64 as a native build tool `dotnet-sdk-native` (In the future this could become mac and windows compatible, but I only yocto on linux)
- Performs the required build steps `dotnet restore` and `dotnet publish -c Release ...`

# Deployment

The resultant application is a self-contained, compressed, trimmed package. No dotnet runtime is required on the target rootfs

Installation path and artifacts path can be configured from the package recipe

* `INSTALL_DIR` can be used to change the default `/opt/dotnet/${PN}` installation directory
* `ENABLE_READYTORUN` can be used to enable/disable AOT (default false)
* `ENABLE_TRIMMING` can be used to enable/disable Trimming (default true)
