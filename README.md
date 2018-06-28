This README file contains information on the contents of the
aspnet layer.

Please see the corresponding sections below for details.


Dependencies
============

This layer depends on:
```
  URI: git://git.openembedded.org/bitbake
  branch: master

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: master

  URI: git://git.yoctoproject.org/meta-mono
  branch: master

  URI: https://github.com/imyller/meta-nodejs
  branch: master
```
The build machine also needs .NET Core installed: https://dot.net/

If you feel adventurous and want to try .NET Core, you'll also need
```
  URI: https://github.com/kraj/meta-clang.git
  branch: morty
```



Patches
=======

You are encouraged to fork this repository and share your patches.

Maintainer: Kai Ruhnau <kai.ruhnau@target-sg.com>


Introduction
============

Since some time now, Microsoft has opened up many of their .NET related projects.
This repository tries to bring this into the OpenEmbedded/Yocto environment.
The main focus at the moment is the ASP.NET Core (formerly ASP.NET 5, formerly ASP.NET vNext)
infrastructure which provides a full server-side HTTP web stack and programming environment.

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
pay Microsoft to give you support. ASP.NET Core is actively tested and verified on Mono by the
ASP.NET team (for example through CI builds) on a "best effort" basis and overall has been
a viable platform. Your author has tested the 1.0 RTM version with mono 4.4 on his embedded
i.MX6 ARM platform.

In November 2016, Microsoft announced upcoming support for Linux/ARM. There are some 
preliminary recipes that try to bring up a .NET Core environment in your distribution.
So far, both CoreCLR and CoreFX are included and an initial experience based on the
`corerun` executable. This boots a simple CLR host, but doesn't include the NuGet package
management facilities the `dotnet` host offers.

Running simple, published "Hello World" applications (`dotnet new`) works, ASP.NET Core web
applications (`dotnet new -t web`) succeed in serving static files, but complicated workloads
like Razor view compilation need the more sophisticated host.
