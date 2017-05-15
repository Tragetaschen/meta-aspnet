CORE_OS_NAME = "Linux"
CORE_BUILD_CONFIG = "Release"
# We need to use a name the buildsystem is familiar with, but it
# shouldn't really matter since we're building everything from scratch.
CORE_RUNTIME_ID_BASE = "linux"
CORE_RUNTIME_ID = "${CORE_RUNTIME_ID_BASE}-${TARGET_ARCH}"

# The following is a consistent set of commits and versions for the three repos
# First look at https://github.com/dotnet/core-setup/blob/master/pkg/projects/Microsoft.NETCore.App/project.json.template
# and choose a commit from @dotnet-bot
CORE_SETUP_SRCREV = "1ff021936263d492539399688f46fd3827169983"
# CoreCLR version is from the "transport.Microsoft.NETCore.Runtime.CoreCLR" entry
CORECLR_BUILD_MAJOR = "25302"
CORECLR_BUILD_MINOR = "01"
# CoreFX version is from the "Microsoft.Private.CoreFx.NETCoreApp" entry
COREFX_BUILD_MAJOR = "25305"
COREFX_BUILD_MINOR = "02"
# Then search for a commit by @dotnet-bot for that versions in
# both https://github.com/dotnet/coreclr/blob/master/dependencies.props
CORECLR_SRCREV = "ea50f78e431f325c4382c21e9b91c3e76898ba1e"
# and https://github.com/dotnet/corefx/blob/master/dependencies.props
COREFX_SRCREV = "af7d2ae49c79bb279625de4e92f7f91f9fe94f5d"
