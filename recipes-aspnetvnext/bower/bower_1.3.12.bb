SRC_URI="https://github.com/bower/${PN}/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
LICENSE="MIT"

LIC_FILES_CHKSUM="file://LICENSE;md5=5bc60e5c591ed5af7f539df58c7d3a7b"
SRC_URI[md5sum]="a0740ae09334303f07d617d24d9eb103"
SRC_URI[sha256sum]="6ba9fecf3491ae9f97b37dc1bea4de8790aa67c4f238ef6410d2fb9d957326d5"

inherit npm-global

do_clean() {
	rm -f ${STAGING_DIR_NATIVE}/usr/bin/bower
	rm -rf ${STAGING_DIR_NATIVE}/usr/lib/node_modules/${PN}
}
