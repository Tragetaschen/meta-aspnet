LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://LICENSE.txt;md5=18d62531c894e91f9f01b5146b73468d"
RDEPENDS_${PN}+="mono"
BBCLASSEXTEND=+"native"
DEPENDS=+" mono-native vnext-certs-native"

inherit KRuntime-environment

do_compile() {
    sh build.sh
}

do_install() {
    OUT=artifacts/build/KRE-Mono/bin

    install -d ${D}${bindir}
    for i in k klr kpm; do
        target=${D}${bindir}/${i}
        # Update the scripts to look in libdir
        sed -e "2,9d" -e "10iDIR=${libdir}/${PN}" ${S}/${OUT}/${i} > ${target}
        chmod 0755 ${target}
    done

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
