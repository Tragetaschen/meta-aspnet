CORE_OS_NAME = "Linux"
CORE_BUILD_CONFIG = "Release"
# We need to use a name the buildsystem is familiar with, but it
# shouldn't really matter since we're building everything from scratch.
CORE_RUNTIME_ID_BASE = "ubuntu.16.04"
CORE_RUNTIME_ID = "${CORE_RUNTIME_ID_BASE}-${TARGET_ARCH}"

# This version...
CORE_SETUP_SRCREV = "1ef26d3a5c32650516671e31c504682e7280506b"
CORECLR_SRCREV = "e5d6ecbfe30c1824134a290ed5f2f8fb1dc43e7a"
COREFX_SRCREV = "6e837bb7bc3b807b4dcc5f0c8096e82af00fd986"
# ...determines these versions in pkg/projects/Microsoft.NETCore.App/project.json.template
COREFX_BUILD_MAJOR = "25004"
COREFX_BUILD_MINOR = "01"
CORECLR_BUILD_MAJOR = "25006"
CORECLR_BUILD_MINOR = "01"
