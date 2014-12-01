DEPENDS="nodejs-native"

inherit cross

do_install() {
	npm install -g
}
