FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " file://honor-prefix-with-special-folders.patch \
             file://add-libdl-to-dllmap.patch \
             file://fix-invalid-il-0057.patch \
"
