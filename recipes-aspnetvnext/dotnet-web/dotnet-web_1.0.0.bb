inherit dotnet

LICENSE="MIT"
SRC_URI="https://github.com/dotnet/cli/raw/rel/1.0.0/LICENSE;downloadfilename=LICENSE-dotnet"
LIC_FILES_CHKSUM="file://LICENSE-dotnet;md5=42b611e7375c06a28601953626ab16cb"
SRC_URI[md5sum] = "42b611e7375c06a28601953626ab16cb"
SRC_URI[sha256sum] = "9d32889a96a7106bb5da8e8395531fc2889c5c20c00f8442d884ad8f2c04998c"

DEPENDS="bower-native gulp-cli-native"
RDEPENDS_${PN}="libuv"

do_unpack () {
	mkdir -p ${WORKDIR}/${PN}-${PV}
	cp ${DL_DIR}/LICENSE-dotnet ${WORKDIR}/${PN}-${PV}
	cd ${WORKDIR}/${PN}-${PV}
	dotnet new -t Web || true
}

