CORE_OS_NAME = "Linux"
CORE_BUILD_CONFIG = "Release"
# We need to use a name the buildsystem is familiar with, but it
# shouldn't really matter since we're building everything from scratch.
CORE_RUNTIME_ID_BASE = "linux"
CORE_RUNTIME_ID = "${CORE_RUNTIME_ID_BASE}-${TARGET_ARCH}"

# The following is a consistent set of commits and versions for the three repos
# First look at https://github.com/dotnet/core-setup/blob/master/dependencies.props
# and choose a commit from @dotnet-bot
CORE_SETUP_SRCREV = "936a2a6229bdd6385e3e8ab0c32ddf0317ac090c"
# CoreCLR version is from the "<MicrosoftNETCoreRuntimeCoreCLRPackageVersion>" entry
CORECLR_BUILD_MAJOR = "25316"
CORECLR_BUILD_MINOR = "03"
# CoreFX version is from the "<CoreFxVersion>" entry
COREFX_BUILD_MAJOR = "25316"
COREFX_BUILD_MINOR = "01"
# Then search for a commit by @dotnet-bot for that versions in
# both https://github.com/dotnet/coreclr/blob/master/dependencies.props
CORECLR_SRCREV = "36e988ee6593351f1e962118a99154df5eae0254"
# and https://github.com/dotnet/corefx/blob/master/dependencies.props
COREFX_SRCREV = "27d906c831b119d88f27865624a4a5d7a661c3d5"
