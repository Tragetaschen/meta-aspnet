SRC_URI="https://github.com/aspnet/Home/archive/v${PV}.tar.gz;downloadfilename=Home-${PV}.tar.gz"
LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://../../../LICENSE.txt;md5=b6dca064e63c2f038081cd782760b67c"

S="${WORKDIR}/Home-${PV}/samples/${PV}/ConsoleApp"

inherit aspnet5
