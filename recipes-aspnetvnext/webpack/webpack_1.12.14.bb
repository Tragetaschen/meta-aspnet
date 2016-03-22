SRC_URI="https://github.com/webpack/${PN}/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
LICENSE="MIT"

LIC_FILES_CHKSUM="file://LICENSE;md5=ec5e11039f1210a435045bf1607a02d6"
SRC_URI[md5sum]="38c11a14676c89fb73a3ea83a30e8b28"
SRC_URI[sha256sum]="1ff10d1c92ebf916c131e8d2a869200deed20d7c4a45d9a3c53dfd234766444f"

inherit npm-global

do_clean() {
	rm -f ${STAGING_DIR_NATIVE}/usr/bin/${BPN}
	rm -rf ${STAGING_DIR_NATIVE}/usr/lib/node_modules/${BPN}
}
