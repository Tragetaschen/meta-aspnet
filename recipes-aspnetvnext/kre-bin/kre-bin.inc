SRC_URI="${SERVER_URI}/package/KRE-mono45-x86/${PV};downloadfilename=KRE-mono45-x86.${PV}.zip;name=kre;subdir=kre-bin-${PV} \
         https://raw.githubusercontent.com/aspnet/KRuntime/dev/LICENSE.txt;name=license;downloadfilename=KRE-license"
LICENSE="Apache-2.0"			
LIC_FILES_CHKSUM="file://${WORKDIR}/KRE-license;md5=18d62531c894e91f9f01b5146b73468d"
RDEPENDS_${PN}+="mono (=git)"
BBCLASSEXTEND=+"native"

SRC_URI[license.md5sum] = "18d62531c894e91f9f01b5146b73468d"
SRC_URI[license.sha256sum] = "3b8b4437035351a81b3b808172c5c4133eafd86480ee41a67a080ee973dc3897"

do_install() {
    install -d ${D}${bindir}
    for i in k klr kpm; do
        target=${D}${bindir}/${i}
        # Update the scripts to look in libdir
        sed -e "2,9d" -e "10iDIR=${libdir}/${PN}" ${S}/bin/${i} > ${target}
        chmod 0755 ${target}
    done

    install -d ${D}${libdir}/${PN}/lib
    for i in ${S}/bin/*.dll; do
        install $i ${D}${libdir}/${PN}
    done
    
    for i in ${S}/bin/lib/*; do
        dirname=$(basename "$i")
        install -d ${D}${libdir}/${PN}/lib/${dirname}
        for j in ${S}/bin/lib/${dirname}/*.dll; do
            install $j ${D}${libdir}/${PN}/lib/${dirname}
        done
    done
}
