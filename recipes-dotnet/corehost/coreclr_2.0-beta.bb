DESCRIPTION = ".NET Core Common Language Runtime (CoreCLR)"
HOMEPAGE = "http://dot.net/"
LICENSE = "MIT"
SECTION = "devel"

DEPENDS = "clang-native lldb libunwind gettext icu openssl util-linux cmake-native"
RDEPENDS_${PN} = "libicuuc libicui18n"

include core-setup-common.inc

SRC_URI = "git://github.com/dotnet/coreclr.git;branch=master; \
           file://toolchain.patch; \
           file://0001-Allow-overriding-target-rid.patch \
           "

PV = "2.0-${CORECLR_BUILD_MAJOR}-${CORECLR_BUILD_MINOR}"

SRCREV = "${CORECLR_SRCREV}"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=ff80286dabb97a39584a14a3edd91cf2"
S = "${WORKDIR}/git"

do_fix_target_name() {
	sed -i s/arm-linux-gnueabihf/${TARGET_SYS}/g ${S}/cross/arm/toolchain.cmake
}

addtask fix_target_name after do_patch before do_configure

do_compile() {
	cd ${S}
	unset bindir
	YOCTO_FORCE_RID=${CORE_RUNTIME_ID} ROOTFS_DIR=${STAGING_DIR_HOST} GCC_TOOLCHAIN=${STAGING_BINDIR_TOOLCHAIN} BuildNumberMajor=${CORECLR_BUILD_MAJOR} BuildNumberMinor=${CORECLR_BUILD_MINOR} ./build.sh ${TARGET_ARCH} release cross skiptests
}

do_install() {
	export src="${S}/bin/Product/Linux.arm.Release"
	export target="${D}/opt/dotnet/"

	install -d ${target}

	install -m 0755 ${src}/corerun ${target}
	install -m 0755 ${src}/libclrjit.so ${target}
	install -m 0755 ${src}/libcoreclr.so ${target}
	install -m 0755 ${src}/libdbgshim.so ${target}
	install -m 0755 ${src}/libmscordaccore.so ${target}
	install -m 0755 ${src}/libmscordbi.so ${target}
	install -m 0755 ${src}/libsos.so ${target}
	install -m 0755 ${src}/libsosplugin.so ${target}
	install -m 0755 ${src}/System.Globalization.Native.so ${target}
	#install -m 0755 ${src}/coreconsole ${D}/%{dotnetfwdir}
	#install -m 0755 ${src}/crossgen ${D}/%{dotnetfwdir}
	#install -m 0755 ${src}/ilasm ${D}/%{dotnetfwdir}
	#install -m 0755 ${src}/ildasm ${D}/%{dotnetfwdir}
	install -m 0755 ${src}/mscorlib.dll ${target}
	install -m 0755 ${src}/System.Private.CoreLib.dll ${target}
	install -m 0755 ${src}/SOS.NETCore.dll ${target}

	# Create dev package
	install -d ${D}/opt/dotnet-nupkg/
	for i in `ls ${src}/.nuget/pkg/*.nupkg`
	do
		install -m 644 ${i} ${D}/opt/dotnet-nupkg/
	done
}

FILES_${PN} = "/opt/dotnet"
FILES_${PN}-dev = "/opt/dotnet-nupkg"

sysroot_stage_all_append () {
    sysroot_stage_dir ${D}/opt/dotnet-nupkg ${SYSROOT_DESTDIR}/opt/dotnet-nupkg
}
