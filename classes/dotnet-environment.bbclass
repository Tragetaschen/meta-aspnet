export NUGET_PACKAGES = "${DL_DIR}/.nuget"
export DOTNET_REFERENCE_ASSEMBLIES_PATH = "${STAGING_LIBDIR_NATIVE}/mono/xbuild-frameworks"

# see https://github.com/dotnet/netcorecli-fsc/wiki/.NET-Core-SDK-1.0.1#using-net-framework-as-targets-framework-the-osxunix-build-fails
export FrameworkPathOverride = "${STAGING_LIBDIR_NATIVE}/mono/4.7.1-api/"
