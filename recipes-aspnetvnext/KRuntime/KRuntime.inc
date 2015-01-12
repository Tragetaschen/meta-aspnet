LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://LICENSE.txt;md5=18d62531c894e91f9f01b5146b73468d"
RDEPENDS_${PN}+="mono"
BBCLASSEXTEND=+"native"
DEPENDS=+" mono-native vnext-certs-native"

inherit KRuntime-environment
export KRE_USER_HOME = "${DL_DIR}/.kre"

do_compile() {
    sh build.sh
}

do_install() {
    OUT=artifacts/build/KRE-Mono/bin

    install -d ${D}${bindir}
    echo "#!/bin/bash
exec ${bindir}/klr ${libdir}/${PN}/Microsoft.Framework.ApplicationHost.dll \"\$@\"
" > ${D}${bindir}/k
    echo "#!/bin/bash
exec ${bindir}/klr ${libdir}/${PN}/lib/Microsoft.Framework.PackageManager/Microsoft.Framework.PackageManager.dll \"\$@\"
" > ${D}${bindir}/kpm
    echo "#!/bin/bash
exec ${bindir}/mono \$MONO_OPTIONS ${libdir}/${PN}/klr.mono.managed.dll \"\$@\"
" > ${D}${bindir}/klr

    chmod 0755 ${D}${bindir}/k
    chmod 0755 ${D}${bindir}/kpm
    chmod 0755 ${D}${bindir}/klr

    install -d ${D}${libdir}/${PN}/lib
    for i in ${S}/${OUT}/*.dll; do
        install $i ${D}${libdir}/${PN}
    done
    
    for i in ${S}/${OUT}/lib/*; do
        dirname=$(basename "$i")
        install -d ${D}${libdir}/${PN}/lib/${dirname}
        for j in ${S}/${OUT}/lib/${dirname}/*.dll; do
            install $j ${D}${libdir}/${PN}/lib/${dirname}
        done
    done
}
