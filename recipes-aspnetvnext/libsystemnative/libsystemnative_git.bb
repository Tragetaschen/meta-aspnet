SUMMARY = "Quick, focused, and hacked library to make ASP.NET Core RTM run on Linux/ARM"
HOMEPAGE = "http://github.com/Tragetaschen/libSystem.Native"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=fe43f7873862a42f1bee07a526a1deac"

SRC_URI = "git://github.com/Tragetaschen/libSystem.Native.git"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

inherit autotools

