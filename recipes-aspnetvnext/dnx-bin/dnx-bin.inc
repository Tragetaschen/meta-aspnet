SRC_URI="https://www.nuget.org/api/v2/package/dnx-mono/${PV};downloadfilename=dnx-mono.${PV}.zip;name=dnx;subdir=dnx-bin-${PV} \
         https://raw.githubusercontent.com/aspnet/dnx/dev/LICENSE.txt;name=license;downloadfilename=dnx-license"
LICENSE="Apache-2.0"			
LIC_FILES_CHKSUM="file://${WORKDIR}/dnx-license;md5=b6dca064e63c2f038081cd782760b67c"
RDEPENDS_${PN}+="mono"
BBCLASSEXTEND=+"native"

SRC_URI[license.md5sum] = "b6dca064e63c2f038081cd782760b67c"
SRC_URI[license.sha256sum] = "6c9045c1cf391713484e2b7ee3e766ceae5f4696c111abaf53ed5e23e129ce24"

S="${WORKDIR}/dnx-bin-${PV}"

do_install() {
    install -d ${D}${bindir}

    echo "#!/bin/bash
exec mono \"${libdir}/${PN}/Microsoft.Dnx.Host.Mono.dll\" \"\$@\"
" > ${D}${bindir}/dnx
    chmod 0755 ${D}${bindir}/dnx

    echo "#!/bin/bash
exec dnx \"${libdir}/${PN}/lib/Microsoft.Dnx.Tooling/Microsoft.Dnx.Tooling.dll\" \"\$@\"
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
