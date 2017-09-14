inherit dotnet-environment

DEPENDS +=" mono-native"
RDEPENDS_${PN}+=" mono libsystemnative"

BUILD="${WORKDIR}/build"
PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN} = "/opt/${PN}"
FILES_${PN}-dbg = "/opt/${PN}/*.pdb"

# Some packages (EF for sqlite, Kestrel) have *.so files bundle.
# This uses a sledge hammer aproach to fix the build
PACKAGEBUILDPKGD_remove = "split_and_strip_files"
INSANE_SKIP_${PN} = "file-rdeps arch"

TARGET_FRAMEWORK ?= "net47"

do_configure () {
    dotnet restore -r unix
}

do_compile () {
    dotnet publish -c Release -o ${BUILD} -r unix -f ${TARGET_FRAMEWORK}
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -dRf ${BUILD}/* ${D}/opt/${PN}
    find ${D}/opt/${PN} -type d -exec chmod 755 {} +
    find ${D}/opt/${PN} -type f -exec chmod 644 {} +
    chmod 755 ${D}/opt/${PN}/*.exe
}

