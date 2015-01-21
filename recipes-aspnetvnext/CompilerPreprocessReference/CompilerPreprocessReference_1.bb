SRC_URI="git://github.com/Tragetaschen/${PN}"
SRCREV="${AUTOREV}"
LICENSE="MIT"
LIC_FILES_CHKSUM="file://../../LICENSE.txt;md5=18d62531c894e91f9f01b5146b73468d"

S="${WORKDIR}/git/src/Source"

inherit aspnet5
