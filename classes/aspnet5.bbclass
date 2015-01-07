DEPENDS=+" mono-native vnext-certs-native KRuntime-native"
RDEPENDS_${PN}+="KRuntime"

BUILD="${WORKDIR}/build"
FILES_${PN} += "/opt/${PN}"

inherit KRuntime-environment

do_compile () {
    kpm restore
    kpm pack --no-source --quiet -o ${BUILD}
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -af ${BUILD}/* ${D}/opt/${PN}
}

