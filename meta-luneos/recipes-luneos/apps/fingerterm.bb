SUMMARY = "A terminal emulator with a custom virtual keyboard"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "1.0.3+gitr${SRCPV}"
SRCREV = "875aa14ed2a511b7e79f29020d40f5b3c344423d"

DEPENDS = "qtbase qtdeclarative"

SRC_URI = " \
    git://git.merproject.org/mer-core/fingerterm.git;protocol=git;branch=master \
    file://appinfo.json \
"
S = "${WORKDIR}/git"

EXTRA_QMAKEVARS_PRE = "\
    DEFAULT_FONT=Courier \
"

inherit webos_ports_fork_repo
inherit webos_filesystem_paths
inherit qmake5

do_install() {
    install -d ${D}${webos_applicationsdir}/fingerterm

    install -m 0644 ${WORKDIR}/appinfo.json ${D}${webos_applicationsdir}/fingerterm/
    install -m 0755 ${WORKDIR}/build/fingerterm ${D}${webos_applicationsdir}/fingerterm/
    install -m 0644 ${S}/fingerterm.png ${D}${webos_applicationsdir}/fingerterm/icon.png

    # Always provide same version as we have in our recipe
    sed -i -e s:__VERSION__:${PV}:g ${D}${webos_applicationsdir}/fingerterm/appinfo.json
}

FILES_${PN}-dbg += "${webos_applicationsdir}/fingerterm/.debug"
FILES_${PN} += "${webos_applicationsdir}"