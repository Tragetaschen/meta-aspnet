DEPENDS=+"mono-native"
LICENSE="CLOSED"

FILES_${PN} += " ${datadir}/.mono"

do_fetch() {
	yes yes | PSEUDO_PREFIX=${S}/usr certmgr -ssl -m https://go.microsoft.com
	yes yes | PSEUDO_PREFIX=${S}/usr certmgr -ssl -m https://nugetgallery.blob.core.windows.net
	yes yes | PSEUDO_PREFIX=${S}/usr certmgr -ssl -m https://nuget.org
}

do_install_append() {
	install -d ${D}${datadir}
	cp -af ${S}/usr/share/.mono ${D}${datadir}/.mono
}

