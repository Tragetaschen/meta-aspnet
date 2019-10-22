inherit insane

export NUGET_PACKAGES = "${DL_DIR}/.nuget"

RDEPENDS_${PN}+="libsqlite3 libcurl zlib libgssapi-krb5 libssl10"
RDEPENDS_${PN}+="${@'' if d.getVar('RID_PARAMETER') else 'dotnet-source-build'}"

BUILD="${WORKDIR}/build"
PACKAGES = "${PN}-dbg ${PN}"

FILES_${PN} = "/opt/${PN}"
FILES_${PN}-dbg = "/opt/${PN}/*.pdb"

# .NET Core ships already striped libraries
PACKAGEBUILDPKGD_remove = "split_and_strip_files"

# libcoreclr.so up until version 2.2 (current as of this writing)
# has relocations in .text
INSANE_SKIP_${PN} = "textrel"

# All PE32 executables would result in the package getting an RDEPENDS
# for mono. Skip the entire check...
SKIP_FILEDEPS = "1"

# There are static libraries that would trigger the QA check.
# Not sure if those are required on the target, so silence the error
INSANE_SKIP = "staticdev"

TARGET_FRAMEWORK ?= "netcoreapp2.2"
RID_PARAMETER ?= "-r linux-${HOST_ARCH}"
RID_PARAMETER_x86-64 = "-r linux-x64"
MSBUILD_EXTRA_ARGS ?= ""
EXECUTABLE ?= "${PN}"

do_configure () {
    dotnet restore ${RID_PARAMETER} ${MSBUILD_EXTRA_ARGS}
}

do_compile () {
    rm -rf ${BUILD}
    dotnet publish -c Release -o ${BUILD} -f ${TARGET_FRAMEWORK} ${RID_PARAMETER} ${MSBUILD_EXTRA_ARGS}
}

do_install () {
    install -d ${D}/opt/${PN}
    cp -dRf ${BUILD}/* ${D}/opt/${PN}
    find ${D}/opt/${PN} -type d -exec chmod 755 {} +
    find ${D}/opt/${PN} -type f -exec chmod 644 {} +
    if [ -n "${RID_PARAMETER}" ]; then
        chmod a+x ${D}/opt/${PN}/${EXECUTABLE}
    fi
}

