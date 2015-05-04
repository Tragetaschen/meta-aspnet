SRC_URI="https://www.nuget.org/api/v2/package/dnx-mono/${PV};downloadfilename=dnx-mono.${PV}.zip;name=dnx;subdir=dnx-bin-${PV} \
         https://raw.githubusercontent.com/aspnet/dnx/dev/LICENSE.txt;name=license;downloadfilename=dnx-license"
LICENSE="Apache-2.0"			
LIC_FILES_CHKSUM="file://${WORKDIR}/dnx-license;md5=18d62531c894e91f9f01b5146b73468d"
RDEPENDS_${PN}+="mono"
BBCLASSEXTEND=+"native"

SRC_URI[license.md5sum] = "18d62531c894e91f9f01b5146b73468d"
SRC_URI[license.sha256sum] = "3b8b4437035351a81b3b808172c5c4133eafd86480ee41a67a080ee973dc3897"

S="${WORKDIR}/dnx-bin-${PV}"

do_install() {
    install -d ${D}${bindir}

    echo "#!/bin/bash
exec mono \"${libdir}/${PN}/dnx.mono.managed.dll\" \"\$@\"
" > ${D}${bindir}/dnx
    chmod 0755 ${D}${bindir}/dnx

    echo "#!/bin/bash
exec dnx \"${libdir}/${PN}/lib/Microsoft.Framework.PackageManager/Microsoft.Framework.PackageManager.dll\" \"\$@\"
" > ${D}${bindir}/dnu
    chmod 0755 ${D}${bindir}/dnu

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
