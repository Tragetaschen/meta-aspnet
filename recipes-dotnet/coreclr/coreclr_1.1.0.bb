DESCRIPTION = ".NET Core Common Language Runtime (CoreCLR)"
HOMEPAGE = "http://dot.net/"
LICENSE = "MIT"
SECTION = "devel"

DEPENDS = "clang-native lldb libunwind gettext icu openssl util-linux cmake-native"
RDEPENDS_${PN} = "libicuuc libicui18n"

SRC_URI = "git://github.com/dotnet/coreclr.git;branch=release/${PV};\
    file://0001-Add-missing-std-move-to-one-exception-throw.patch \
    file://0002-Fix-building-CoreCLR-with-Clang-3.9.patch \
    file://0003-Fix-ARM-issue-with-undefined-interopsafeEXEC.patch \
    file://toolchain.patch \
"
SRCREV = "release/${PV}"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=ff80286dabb97a39584a14a3edd91cf2"
S = "${WORKDIR}/git"

do_configure() {
	cd ${S}
	ROOTFS_DIR=${STAGING_DIR_HOST} ./build.sh arm release cross skipgenerateversion -skiprestore skipnuget configureonly cmakeargs "-DFEATURE_GDBJIT=TRUE"
}

do_compile() {
	cd ${S}
	ROOTFS_DIR=${STAGING_DIR_HOST} ./build.sh arm release cross skipgenerateversion -skiprestore skipnuget skipconfigure cmakeargs "-DFEATURE_GDBJIT=TRUE"
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
}

FILES_${PN} = "/opt/dotnet"

