SUMMARY = "This is the web application launcher for the Luna Next webOS UI platform."
LICENSE = "GPL-3.0 & Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=0f93d2cf04b94ac3f04a789a1fb11ead \
    file://COPYING-Apache-2.0;md5=3b83ef96387f14655fc854ddc3c6bd57 \
"

DEPENDS = "qtbase qtdeclarative qtwebengine luna-sysmgr-common libconnman-qt5"
RDEPENDS_${PN} += " \
    qtdeclarative-qmlplugins \
    qtwebengine-qmlplugins \
"

PV = "0.2.0-8+git${SRCPV}"
SRCREV = "73c8211126d95a7cb96fd768ac586896011023a1"

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

inherit pkgconfig
inherit webos_system_bus
inherit webos_ports_repo
inherit webos_cmake_qt5
inherit webos_filesystem_paths
inherit webos_systemd

FILES_${PN} += "${webos_frameworksdir}"

PNBLACKLIST[luna-webappmanager] ?= "BROKEN: needs to be adapted to new qt-5.7"
