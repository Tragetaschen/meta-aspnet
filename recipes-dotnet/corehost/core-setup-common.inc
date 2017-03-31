CORE_OS_NAME = "Linux"
CORE_BUILD_CONFIG = "Release"
# We need to use a name the buildsystem is familiar with, but it
# shouldn't really matter since we're building everything from scratch.
CORE_RUNTIME_ID_BASE = "linux"
CORE_RUNTIME_ID = "${CORE_RUNTIME_ID_BASE}-${TARGET_ARCH}"

# The following is a consistent set of commits and versions for the three repos
# First look at https://github.com/dotnet/core-setup/blob/master/pkg/projects/Microsoft.NETCore.App/project.json.template
# and choose a commit from @dotnet-bot
CORE_SETUP_SRCREV = "f4cdbf3742198d8114303aaf1a40e86a5d167508"
# CoreCLR version is from the "transport.Microsoft.NETCore.Runtime.CoreCLR" entry
CORECLR_BUILD_MAJOR = "25221"
CORECLR_BUILD_MINOR = "02"
# CoreFX version is from the "Microsoft.Private.CoreFx.NETCoreApp" entry
COREFX_BUILD_MAJOR = "25221"
COREFX_BUILD_MINOR = "02"
# Then search for a commit by @dotnet-bot for that versions in
# both https://github.com/dotnet/coreclr/blob/master/dependencies.props
CORECLR_SRCREV = "0c98335f683ff806b7d282757fb96d8dc8de74e6"
# and https://github.com/dotnet/corefx/blob/master/dependencies.props
COREFX_SRCREV = "55b0b4519430f05cb68244a88c061afee0958063"
