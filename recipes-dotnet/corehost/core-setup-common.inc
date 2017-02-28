CORE_OS_NAME = "Linux"
CORE_BUILD_CONFIG = "Release"
# We need to use a name the buildsystem is familiar with, but it
# shouldn't really matter since we're building everything from scratch.
CORE_RUNTIME_ID_BASE = "ubuntu.16.04"
CORE_RUNTIME_ID = "${CORE_RUNTIME_ID_BASE}-${TARGET_ARCH}"

# This version...
CORECLR_SRCREV = "7c252140f3db634565baa1bc5075ebc47186a772"
COREFX_SRCREV = "c015cf7e2567b9f66098ec4cf6176efa16a13298"
CORE_SETUP_SRCREV = "593fbd48afd7ae8a2d3826ee2d12ec844cbff7c2"
# ...determines these versions in pkg/projects/Microsoft.NETCore.App/project.json.template
CORECLR_BUILD_MAJOR = "25013"
CORECLR_BUILD_MINOR = "01"
COREFX_BUILD_MAJOR = "25011"
COREFX_BUILD_MINOR = "01"
