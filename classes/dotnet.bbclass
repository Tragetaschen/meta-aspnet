inherit dotnet-environment

DEPENDS +=" mono-native"
RDEPENDS_${PN}+=" mono libsystemnative"

BUILD="${WORKDIR}/build"
FILES_${PN} += "/opt/${PN}"

# Some packages (EF for sqlite, Kestrel) have *.so files bundle.
# This uses a sledge hammer aproach to fix the build
PACKAGEBUILDPKGD_remove = "split_and_strip_files"
INSANE_SKIP_${PN} = "file-rdeps arch"

do_configure () {
    dotnet restore -r unix
}

do_compile () {
    dotnet publish -c Release -o ${BUILD} -r unix
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -dRf ${BUILD}/* ${D}/opt/${PN}
}

