SRC_URI="http://www.myget.org/F/aspnetvnext/api/v2/package/KRE-mono45-x86/${PV};downloadfilename=KRE-mono45-x86.${PV}.zip;name=kre;subdir=${PN}-${PV} \
         https://raw.githubusercontent.com/aspnet/KRuntime/dev/LICENSE.txt;name=license;downloadfilename=KRE-license"
LICENSE="Apache-2.0"			
LIC_FILES_CHKSUM="file://${WORKDIR}/KRE-license;md5=18d62531c894e91f9f01b5146b73468d"

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
