DESCRIPTION = "corehost"
HOMEPAGE = "http://dot.net/"
LICENSE = "MIT"
SECTION = "devel"

DEPENDS = "clang-native corefx coreclr cmake-native openssl"
RDEPENDS_${PN} = "libssl libicuuc libicui18n libcurl"

SRC_URI = "git://github.com/dotnet/core-setup.git;nobranch=1 \
           file://0003-cross-toolchain-Use-clang-properly.patch \
           "

SRCREV = "${CORE_SETUP_SRCREV}"

PV = "2.0-${COREFX_BUILD_MAJOR}-${COREFX_BUILD_MINOR}"

S = "${WORKDIR}/git"

# Silence some QA warnings, let's not patch the build any more
INSANE_SKIP_${PN} += "staticdev ldflags already-stripped"

FILES_${PN} = "/opt/dotnet"

