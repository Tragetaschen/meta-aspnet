DEPENDS=+" mono-native vnext-certs-native XRE-bin-native"
RDEPENDS_${PN}+="XRE-bin"

BUILD="${WORKDIR}/build"
FILES_${PN} += "/opt/${PN}"

inherit XRE-environment

do_compile () {
    kpm restore
    kpm bundle --no-source --quiet -o ${BUILD}
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -af ${BUILD}/* ${D}/opt/${PN}
}

