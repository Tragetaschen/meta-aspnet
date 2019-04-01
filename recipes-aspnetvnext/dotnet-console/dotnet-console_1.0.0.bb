inherit dotnet

LICENSE="MIT"
SRC_URI="https://raw.githubusercontent.com/dotnet/templating/release/2.2/LICENSE;downloadfilename=LICENSE-dotnetconsoletemplate"
LIC_FILES_CHKSUM="file://LICENSE-dotnetconsoletemplate;md5=114cde7d533aa83500070c86cb529bb4"
SRC_URI[md5sum] = "114cde7d533aa83500070c86cb529bb4"
SRC_URI[sha256sum] = "715c0e735e3f43b9f9397c5403ed352b913527cf16d820c14e2dceefd8331b44"

S="${WORKDIR}/${PN}"
RID_PARAMETER = ""

do_unpack () {
	mkdir -p ${WORKDIR}/${PN}
	cp ${DL_DIR}/LICENSE-dotnetconsoletemplate ${WORKDIR}/${PN}
	cd ${WORKDIR}/${PN}
	dotnet new console
}

