DEPENDS=+" mono-native dnx-bin-native"
RDEPENDS_${PN}+="dnx-bin"

BUILD="${WORKDIR}/build"
FILES_${PN} += "/opt/${PN}"

inherit dnx-environment

do_compile () {
    dnu restore
    dnu publish --no-source --quiet -o ${BUILD}
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -af ${BUILD}/* ${D}/opt/${PN}
}

