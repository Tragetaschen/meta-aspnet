FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " file://honor-prefix-with-special-folders.patch \
             file://enable-profile4_5-scripts.patch \
             file://add-libdl-to-dllmap.patch \
             file://0001-Populate-the-RequestingAssembly-property-of-ResolveE.patch \
"

