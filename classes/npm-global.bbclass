DEPENDS="nodejs-native"

inherit cross

do_compile() {
	NPM_CONFIG_CACHE=${DL_DIR}/.npm npm install
}

do_install() {
	NPM_CONFIG_CACHE=${DL_DIR}/.npm npm install -g
}
