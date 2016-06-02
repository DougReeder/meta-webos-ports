SUMMARY = "Instant Messaging service"
SECTION = "webos/services"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=263f341c01743dbd6b06ae75369dbeed \
    file://COPYRIGHT;md5=2ce083d13f0f21e5207b4115c8926450 \
"

DEPENDS = "glib-2.0 db8 pidgin luna-service2 tidy-html5"

PV = "3.0.5+gitr${SRCPV}"
SRCREV = "f6aaac0fe1292133fd04093b9531f55ba1fab62e"

inherit webos_ports_repo
inherit webos_cmake
inherit pkgconfig
inherit webos_system_bus

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install_append() {
    cp -R --no-dereference --preserve=mode,links -v ${S}/files/etc ${D}
    cp -R --no-dereference --preserve=mode,links -v ${S}/files/usr ${D}
}

CXXFLAGS += "-fpermissive"
