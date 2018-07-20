This README file contains information on the contents of the
aspnet layer.

Please see the corresponding sections below for details.


Dependencies
============

This layer depends on:
```
  URI: git://git.openembedded.org/bitbake
  branch: pyro

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: pyro

  URI: git://git.yoctoproject.org/meta-mono
  branch: pyro

  URI: https://github.com/imyller/meta-nodejs
  branch: pyro
```
The build machine also needs .NET Core installed: https://dot.net/

If you feel adventurous and want to try .NET Core, you'll also need
```
  URI: https://github.com/kraj/meta-clang.git
  branch: pyro
```



Patches
=======

You are encouraged to fork this repository and share your patches.

Maintainer: Kai Ruhnau <kai.ruhnau@target-sg.com>


Introduction
============

Since some time now, Microsoft has opened up many of their .NET related projects.
This repository tries to bring this into the OpenEmbedded/Yocto environment.
The main focus at the moment is the ASP.NET Core infrastructure which provides
a full server-side HTTP web stack and programming environment.

There is a `dotnet.bbclass` that helps building ASP.NET Core projects.
As samples for its usage, the `dotnet-console` and `dotnet-web` recipes
are provided and build the `dotnet new` console and web application respectively.
These projects are based off of .NET Core and don't yet run in an actual image (see below).
They serve as a test bed for the build infrastructure. Actual projects have to target the
"net471" framework or similar to run with Mono >= 5.12.


Adding the aspnet layer to your build
=====================================

In order to use this layer, you need to make the build system aware of
it.

Assuming the aspnet layer exists at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the aspnet layer to bblayers.conf, along with any
other layers needed. e.g.:

```
  BBLAYERS ?= " \
    /path/to/yocto/meta \
    /path/to/yocto/meta-yocto \
    /path/to/yocto/meta-yocto-bsp \
    /path/to/yocto/meta-mono \
    /path/to/yocto/meta-aspnet \
    "
```

.NET Runtime
============

There has always been Mono available as the .NET implementation outside the Windows universe.
Mono as runtime isn't "officially" supported by Microsoft, but that only means that you cannot
pay Microsoft to give you support. Your author has tested the 2.1 RTM version with mono 5.12
on his embedded i.MX6 ARM platform.

Since version 2.1, there's official support for Linux/ARM in .NET Core. This layer
contains a dotnet-source-build recipe that's based off of https://github.com/dotnet/source-build
and compiles a runtime and shared library of .NET Core, but not the SDK.
The source-build build process isn't yet very compatible with the cross-compilation
environment in bitbake, so for the time being these recipes are limited to ARM.
.NET Core has official support for x86 and x64 and it might be possible to adapt the recipe
for those. Aarch64/ARM64 is very close to being supported by .NET Core, but the 2.1 release
doesn't yet. If you want to try that and adapt the recipe accordingly, I'd appreciate a PR.

Linux/ARM on Yocto sumo was found to currently *not* work (https://github.com/dotnet/coreclr/issues/19025).

