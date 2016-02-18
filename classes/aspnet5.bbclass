inherit dnx-environment

DEPENDS +=" mono-native dnx-bin-native"
RDEPENDS_${PN}+=" dnx-bin"

BUILD="${WORKDIR}/build"
FILES_${PN} += "/opt/${PN}"

ADDITIONAL_RESTORE_PACKAGES = ""

do_configure () {
    for i in ${ADDITIONAL_RESTORE_PACKAGES}; do
        dnu restore $i
    done
    dnu restore .
}

do_compile () {
    dnu publish --no-source --quiet -o ${BUILD}
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -af ${BUILD}/* ${D}/opt/${PN}
}

