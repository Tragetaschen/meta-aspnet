CORE_OS_NAME = "Linux"
CORE_BUILD_CONFIG = "Release"
# We need to use a name the buildsystem is familiar with, but it
# shouldn't really matter since we're building everything from scratch.
CORE_RUNTIME_ID_BASE = "linux"
CORE_RUNTIME_ID = "${CORE_RUNTIME_ID_BASE}-${TARGET_ARCH}"

# The following are the revs of the v2.0.0-preview2 tags in each repository
CORE_SETUP_SRCREV = "40c565230930ead58a50719c0ec799df77bddee9"
CORECLR_SRCREV = "9679ded412d1421f3d54ee3082e8b5f900217a4a"
COREFX_SRCREV = "029cf57733b7ec6c1dd8a13857e800b9a8d97192"

# See: https://github.com/dotnet/core-setup/blob/master/dependencies.props
# CoreCLR version is from the "<MicrosoftNETCoreRuntimeCoreCLRPackageVersion>" entry
CORECLR_BUILD_MAJOR = "25407"
CORECLR_BUILD_MINOR = "01"
# CoreFX version is from the "<CoreFxVersion>" entry
COREFX_BUILD_MAJOR = "25405"
COREFX_BUILD_MINOR = "01"
