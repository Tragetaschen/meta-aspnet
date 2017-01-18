FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Add the non-upstream patch from:
# https://github.com/dotnet/coreclr/blob/master/Documentation/building/linux-instructions.md#build-for-armlinux
SRC_URI_append += "file://expand-arm-Validate-memory-before-access.patch"
