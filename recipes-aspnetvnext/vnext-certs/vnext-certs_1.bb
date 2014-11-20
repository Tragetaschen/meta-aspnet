DEPENDS=+" mono-native"
LICENSE="CLOSED"
BBCLASSEXTEND=+"native"

FILES_${PN} += " ${datadir}/.mono"

inherit KRuntime-environment

do_compile() {
	export PSEUDO_PREFIX=${S}/usr
	yes yes | certmgr -ssl -m https://go.microsoft.com
	yes yes | certmgr -ssl -m https://nugetgallery.blob.core.windows.net
	yes yes | certmgr -ssl -m https://nuget.org
	mozroots --machine --import --sync
}

do_install() {
	install -d ${D}${datadir}
	cp -af ${S}/usr/share/.mono ${D}${datadir}/.mono
}

