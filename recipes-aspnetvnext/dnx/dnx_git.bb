SRC_URI="git://github.com/aspnet/dnx.git;branch=dev"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

include dnx.inc
