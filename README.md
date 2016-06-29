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

The build machine also needs .NET Core installed: https://microsoft.com/net/core


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
"net452" framework or similar to run with Mono.


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

On the horizon, there is a second option for the .NET Runtime, namely .NET Core which is provided
by Microsoft directly. Currently, this runtime is already ported to Windows/ARM and Linux/x64
and porting efforts to Linux/ARM are currently underway with the introduction of [Samsung in the
.NET Foundation Technical Steering Group](http://www.dotnetfoundation.org/blog/samsung-join-tsg).
I expect this repo to include actual .NET Core support in addition to Mono in the future.

