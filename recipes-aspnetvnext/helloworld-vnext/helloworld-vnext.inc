SRC_URI="https://github.com/aspnet/dnx/archive/${PV}.tar.gz;downloadfilename=dnx-${PV}.tar.gz"
LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://../../LICENSE.txt;md5=18d62531c894e91f9f01b5146b73468d"

S="${WORKDIR}/dnx-${PV}/samples/HelloWorld"

inherit aspnet5
