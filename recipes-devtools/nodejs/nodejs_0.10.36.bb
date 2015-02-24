SUMMARY = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "Artistic-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e804bf1e856481415099460bc54d9316"

DEPENDS = "openssl"

inherit pythonnative

SRC_URI = "http://nodejs.org/dist/v${PV}/node-v${PV}.tar.gz"
SRC_URI[md5sum] = "4b3527b830f2dacaba01aececd509c6f"
SRC_URI[sha256sum] = "b9d7d1d0294bce46686b13a05da6fc5b1e7743b597544aa888e8e64a9f178c81"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

ARCHFLAGS_arm = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--with-arm-float-abi=hard', '--with-arm-float-abi=softfp', d)}"
ARCHFLAGS ?= ""

# Node is way too cool to use proper autotools, so we install two wrappers to forcefully inject proper arch cflags to workaround gypi
do_configure () {
    export LD="${CXX}"

    ./configure --prefix=${prefix} --without-snapshot ${ARCHFLAGS}
}

do_compile () {
    export LD="${CXX}"
    make BUILDTYPE=Release
}

do_install () {
    oe_runmake install DESTDIR=${D}

    install -d ${D}${prefix}/etc
    echo "cache = ${DL_DIR}/.npm" > ${D}${prefix}/etc/npmrc
}

RDEPENDS_${PN} = "curl python-shell python-datetime python-subprocess python-crypt python-textutils python-netclient "
RDEPENDS_${PN}_class-native = ""

FILES_${PN} += "${libdir}/node/wafadmin ${libdir}/node_modules ${libdir}/dtrace ${libdir}/* ${prefix}/etc"
BBCLASSEXTEND = "native"
