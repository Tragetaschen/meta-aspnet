DEPENDS +=" mono-native"
RDEPENDS_${PN}+=" mono libsystemnative"

export NUGET_PACKAGES = "${DL_DIR}/.nuget"
export DOTNET_REFERENCE_ASSEMBLIES_PATH = "${STAGING_LIBDIR_NATIVE}/mono/xbuild-frameworks"

# see https://github.com/dotnet/netcorecli-fsc/wiki/.NET-Core-SDK-1.0.1#using-net-framework-as-targets-framework-the-osxunix-build-fails
export FrameworkPathOverride = "${STAGING_LIBDIR_NATIVE}/mono/4.7.1-api/"

BUILD="${WORKDIR}/build"
PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN} = "/opt/${PN}"
FILES_${PN}-dbg = "/opt/${PN}/*.pdb"

# Some packages (EF for sqlite, Kestrel) have *.so files bundle.
# This uses a sledge hammer aproach to fix the build
PACKAGEBUILDPKGD_remove = "split_and_strip_files"
INSANE_SKIP_${PN} = "file-rdeps arch"

MSBUILD_EXTRA_ARGS ?= ""

do_configure () {
    dotnet restore ${MSBUILD_EXTRA_ARGS}
}

do_compile () {
    rm -rf ${BUILD}
    dotnet publish -c Release -o ${BUILD} -f net471 ${MSBUILD_EXTRA_ARGS}
    rm ${BUILD}/System.Diagnostics.Tracing.dll
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -dRf ${BUILD}/* ${D}/opt/${PN}
    find ${D}/opt/${PN} -type d -exec chmod 755 {} +
    find ${D}/opt/${PN} -type f -exec chmod 644 {} +
    chmod 755 ${D}/opt/${PN}/*.exe
}

