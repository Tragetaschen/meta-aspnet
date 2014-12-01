SRC_URI="https://github.com/aspnet/Home/archive/v${PV}.tar.gz;downloadfilename=Home-${PV}.tar.gz"
LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://../../LICENSE.txt;md5=18d62531c894e91f9f01b5146b73468d"

S="${WORKDIR}/Home-${PV}/samples/ConsoleApp"

inherit aspnet5
