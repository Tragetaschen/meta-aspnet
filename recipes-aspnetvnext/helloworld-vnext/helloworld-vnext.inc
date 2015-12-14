SRC_URI="https://github.com/aspnet/dnx/archive/${PV}.tar.gz;downloadfilename=dnx-${PV}.tar.gz"
LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://../../LICENSE.txt;md5=b6dca064e63c2f038081cd782760b67c"

S="${WORKDIR}/dnx-${PV}/samples/HelloWorld"

ADDITIONAL_RESTORE_PACKAGES_append = "../HelloShared"

inherit aspnet5
