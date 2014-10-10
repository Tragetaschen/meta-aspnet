DEPENDS="mono-native vnext-certs-native"

BUILD="${WORKDIR}/build"
FILES_${PN} += "/opt/${PN}"

do_compile () {
    mononativebin=`which mono`
    mononativebinprefix=`dirname ${mononativebin}`
    mononativeprefix=`dirname ${mononativebinprefix}`

    #kpm restore
    PSEUDO_PREFIX=${mononativeprefix} XDG_CONFIG_HOME=${DL_DIR}/kpm-home XDG_DATA_HOME=${DL_DIR}/kpm-data USERPROFILE=${DL_DIR}/kpm-userprofile kpm restore
    #kpm build
    PSEUDO_PREFIX=${mononativeprefix} XDG_CONFIG_HOME=${DL_DIR}/kpm-home XDG_DATA_HOME=${DL_DIR}/kpm-data USERPROFILE=${DL_DIR}/kpm-userprofile kpm build
    #kpm pack
    PSEUDO_PREFIX=${mononativeprefix} XDG_CONFIG_HOME=${DL_DIR}/kpm-home XDG_DATA_HOME=${DL_DIR}/kpm-data USERPROFILE=${DL_DIR}/kpm-userprofile kpm pack --no-source -o ${BUILD}
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -af ${BUILD}/* ${D}/opt/${PN}
}
