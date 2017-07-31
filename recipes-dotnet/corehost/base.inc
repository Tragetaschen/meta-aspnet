export CURL_CA_BUNDLE = "${STAGING_ETCDIR_NATIVE}/ssl/certs/ca-certificates.crt"

# We need to use a name the buildsystem is familiar with, but it
# shouldn't really matter since we're building everything from scratch.
CORE_RUNTIME_ID = "linux-${TARGET_ARCH}"
