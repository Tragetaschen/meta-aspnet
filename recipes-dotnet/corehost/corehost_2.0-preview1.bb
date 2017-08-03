include preview1-revs.inc
include corehost.inc

SRC_URI += "\
  file://0001-build.sh-Support-our-build-preview1.patch \
  file://0001-Port-fix-for-1908.patch \
"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42b611e7375c06a28601953626ab16cb"
PV = "2.0-${COREFX_BUILD_MAJOR}-${COREFX_BUILD_MINOR}"

do_configure() {
    # Double check the configuration. Otherwise corehost may silently download something else.
    grep -q -E '"Microsoft.Private.CoreFx.NETCoreApp":.*${COREFX_BUILD_MAJOR}-${COREFX_BUILD_MINOR}",' ${S}/pkg/projects/Microsoft.NETCore.App/project.json.template || \
        { echo "ERROR: Mismatching CoreFX version in pkg/projects/Microsoft.NETCore.App/project.json.template"; exit 1; }
    grep -q -E '"transport.Microsoft.NETCore.Runtime.CoreCLR":.*${CORECLR_BUILD_MAJOR}-${CORECLR_BUILD_MINOR}",' ${S}/pkg/projects/Microsoft.NETCore.App/project.json.template || \
        { echo "ERROR: Mismatching CoreCLR version in pkg/projects/Microsoft.NETCore.App/project.json.template"; exit 1; }

    sed -i s/arm-linux-gnueabihf/${TARGET_SYS}/g ${S}/cross/arm/toolchain.cmake
    sed -i '/key="dotnet-core"/i <add key="local" value="${STAGING_DIR_HOST}/opt/dotnet-nupkg" />' ${S}/NuGet.Config
}

do_compile() {
    cd ${S}
    # Bitbake sets bindir ("/usr/bin") which MsBuild would happily pick up
    # as BinDir to store the built libraries in
    unset bindir
    ./build.sh -portable --skiptests --env-vars DISABLE_CROSSGEN=1,TARGETPLATFORM=${TARGET_ARCH},TARGETRID=${CORE_RUNTIME_ID},CROSS=1,ROOTFS_DIR=${STAGING_DIR_HOST},CONFIGURATION=Release,GCC_TOOLCHAIN=${STAGING_BINDIR_TOOLCHAIN}
}

do_install() {
    install -d ${D}/opt/dotnet

    cp -dr ${S}/artifacts/${CORE_RUNTIME_ID}/obj/combined-framework-host/* ${D}/opt/dotnet/
}

