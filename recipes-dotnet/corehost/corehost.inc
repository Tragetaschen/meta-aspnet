DESCRIPTION = "corehost"
HOMEPAGE = "http://dot.net/"
LICENSE = "MIT"
SECTION = "devel"

DEPENDS = "clang-native corefx coreclr cmake-native openssl ca-certificates-native"
RDEPENDS_${PN} = "libssl libicuuc libicui18n libcurl"

SRC_URI = "git://github.com/dotnet/core-setup.git;nobranch=1 \
           file://0003-cross-toolchain-Use-clang-properly.patch \
           "

SRCREV = "${CORE_SETUP_SRCREV}"

S = "${WORKDIR}/git"

# Silence some QA warnings, let's not patch the build any more
INSANE_SKIP_${PN} += "staticdev ldflags already-stripped"

include base.inc

FILES_${PN} = "/opt/dotnet"

