SRC_URI="https://github.com/bower/${PN}/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
LICENSE="MIT"

LIC_FILES_CHKSUM="file://LICENSE;md5=135697567327f92e904ef0be2082da5e"
SRC_URI[md5sum]="a927160bf82914a2a2a42d6283b57c61"
SRC_URI[sha256sum]="078048f3c96240dfee7ce60d84d61eac5c62502a9f393eb562335bb140452493"

inherit npm-global

do_clean() {
	rm -f ${STAGING_DIR_NATIVE}/usr/bin/bower
	rm -rf ${STAGING_DIR_NATIVE}/usr/lib/node_modules/${PN}
}
