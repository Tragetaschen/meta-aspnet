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

In addition, there are some recipes that integrate tooling for web client development
as it is the provided default in the "Web Application" project template in Visual Studio.



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
pay Microsoft to give you support. ASP.NET 5 is actively tested and verified on Mono by the
ASP.NET team (for example through CI builds) and overall has been a viable platform.

However, there are subtle differences in the implementations that have bitten ASP.NET 5 in the past and
still do. The current 4.2 versions of Mono (as of 2015-12-16) don't work with ASP.NET 5 and result
in random hangs. I have sucessfully tested `PREFERRED_VERSION_mono[-native]="4.0.4.1"`
on an ARM platform, when the patches in this layer (especially the IL_0057 one) are applied.
But overall, let me emphasize that things keep improving considerably.

On the horizon, there is a second option for the .NET Runtime, namely .NET Core which is provided
by Microsoft directly. Currently, this runtime is ported to Windows/ARM and Linux/x64
and any porting efforts to Linux/ARM are currently (confirmed on 2016-01-05 in the Standup Meeting)
at a low priority. In addition, the build environment for that is hard to set up outside of Ubuntu,
so this repository doesn't yet contain anything in that direction.

