SRC_URI="https://github.com/DefinitelyTyped/${PN}/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
LICENSE="Apache-2.0"

LIC_FILES_CHKSUM="file://LICENSE.txt;md5=585c0b2e399c33bf30e5021bbd5abaa6"
SRC_URI[md5sum]="7827e85f7220543b29eaa45ad139135a"
SRC_URI[sha256sum]="d8c951e8be81b951c13390bc8cb90b9e49d043be089626097ee9e7b8026f0328"

inherit npm-global

do_clean() {
	rm -f ${STAGING_DIR_NATIVE}/usr/bin/${BPN}
	rm -rf ${STAGING_DIR_NATIVE}/usr/lib/node_modules/${BPN}
}
