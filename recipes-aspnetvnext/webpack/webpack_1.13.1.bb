SUMMARY = "Webpack bundler for modules"
HOMEPAGE = "https://webpack.github.io"
SECTION = "js/devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ec5e11039f1210a435045bf1607a02d6"

PACKAGE_ARCH = "all"

SRC_URI = "https://github.com/webpack/webpack/archive/v${PV}.tar.gz"

SRC_URI[md5sum]="a1671ba689cd6ac62f1a2d31b3683fb1"
SRC_URI[sha256sum]="71fa0b52089e9fef7fe7834433489a169f4e77f2dfa4074942b2555a2643a639"

inherit npm-install-global

INSANE_SKIP_${PN} += "file-rdeps"

BBCLASSEXTEND = "native nativesdk"
