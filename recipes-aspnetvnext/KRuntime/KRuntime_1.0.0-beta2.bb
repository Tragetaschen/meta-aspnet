SRC_URI="https://github.com/aspnet/KRuntime/archive/${PV}.tar.gz;downloadfilename=KRE-Mono.${PV}.tar.gz \
         file://roslyn-update.patch"

require KRuntime.inc

SRC_URI[md5sum] = "30384c84575fa28e0d1571d4f2ed5aa0"
SRC_URI[sha256sum] = "2bf63f25ce1ce1eba5c2d12c438ac74718d858e2271df69251a43eb09d85a6e7"
