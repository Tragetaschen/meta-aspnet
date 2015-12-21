SRC_URI="https://github.com/gulpjs/${PN}/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
LICENSE="MIT"

LIC_FILES_CHKSUM="file://LICENSE;md5=56144547c46601ee8caeafc843fd99a6"
SRC_URI[md5sum]="4416063cfa4125ecf45cbf7833674d29"
SRC_URI[sha256sum]="81679f0d462af503a71158bcee44a9ca2ae61b1e1c236b78b4edb4dc7e3296ec"

inherit npm-global

do_clean() {
	rm -f ${STAGING_DIR_NATIVE}/usr/bin/${BPN}
	rm -rf ${STAGING_DIR_NATIVE}/usr/lib/node_modules/${BPN}
}
