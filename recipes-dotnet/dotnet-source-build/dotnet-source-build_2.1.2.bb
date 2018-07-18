DESCRIPTION = "Dotnet from source-build"
HOMEPAGE = "http://dot.net"
LICENSE = "MIT"
SECTION = "devel"

SRC_URI = "\
  gitsm://github.com/dotnet/source-build;branch=release/2.1 \
  file://0001-source-build-CMake-adaptions.patch \
  file://0002-source-build-Disable-rootfs-generation.patch \
  file://0001-coreclr-CMake-adaptions.patch \
  file://0002-coreclr-Compilation-fix.patch \
  file://0003-coreclr-Use-the-existing-environment-variable-for-parallel-m.patch \
  file://0001-corefx-CMake-adaptions.patch \
  file://0002-corefx-ASN1_STRING_print_ex-has-an-unsigned-long-flags-argu.patch \
  file://0001-core-setup-CMake-adaptions.patch \
"
SRCREV="7f9bbac8deb896d25d31f1829c5ee9ea91a24466"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9fc642ff452b28d62ab19b7eea50dfb9"

S = "${WORKDIR}/git"

DEPENDS = "clang-native lldb libunwind gettext icu openssl util-linux cmake-native lttng-ust ca-certificates-native krb5 curl"
RDEPENDS_${PN} = "libicuuc libicui18n lttng-ust libcurl libuv libssl"

INSANE_SKIP_${PN} += "staticdev file-rdeps textrel"
WARN_QA_remove = "libdir"

export CURL_CA_BUNDLE = "${STAGING_ETCDIR_NATIVE}/ssl/certs/ca-certificates.crt"

do_fix_target_name() {
	sed -i s/arm-linux-gnueabihf/${TARGET_SYS}/g ${S}/cross/arm/toolchain.cmake
	sed -i s/arm-linux-gnueabihf/${TARGET_SYS}/g ${S}/src/coreclr/cross/toolchain.cmake
	sed -i s/arm-linux-gnueabihf/${TARGET_SYS}/g ${S}/src/corefx/cross/arm/toolchain.cmake
	sed -i s/arm-linux-gnueabihf/${TARGET_SYS}/g ${S}/src/core-setup/cross/arm/toolchain.cmake
}

addtask fix_target_name after do_patch before do_configure

do_compile() {
	cd ${S}
	unset bindir
	export SOURCE_BUILD_SKIP_SUBMODULE_CHECK=1
	export ArmEnvironmentVariables="ROOTFS_DIR=${STAGING_DIR_HOST}"
	export ROOTFS_DIR=${STAGING_DIR_HOST}
	export GCC_TOOLCHAIN=${STAGING_BINDIR_TOOLCHAIN}
	export PARALLEL_MAKEINST="${PARALLEL_MAKEINST}"
	export VersionUserName=meta-aspnet
	./build.sh /p:Platform=arm /p:Configuration=Release /p:SkipGenerateRootFs=true
}

do_install() {
    install -d ${D}${datadir}/dotnet
    install -d ${D}${bindir}

    cp -dr ${S}/src/core-setup/Bin/obj/*-arm.Release/combined-framework-host/* ${D}${datadir}/dotnet/
    ln -sf ../share/dotnet/dotnet ${D}${bindir}/dotnet
}

FILES_${PN} = "${datadir}/dotnet ${bindir}/dotnet"
