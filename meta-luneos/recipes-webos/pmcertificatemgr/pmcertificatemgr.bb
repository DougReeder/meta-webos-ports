# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Provides certificate storage and wrapper API on openssl certificate manipulation functions"
AUTHOR = "Keith Derrick <keith.derrick@lge.com>"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "openssl glib-2.0"
RDEPENDS_${PN} = "ca-certificates"

PV = "2.0.0-29+git${SRCPV}"
SRCREV = "dc711d0788cd782e543bfa4c82eb23aee238825d"

inherit webos_ports_repo
inherit webos_cmake
inherit pkgconfig
inherit webos_machine_impl_dep
inherit update-alternatives

ALTERNATIVE_${PN} = "openssl-cnf"
ALTERNATIVE_LINK_NAME[openssl-cnf] = "${libdir}/ssl/openssl.cnf"
ALTERNATIVE_PRIORITY[openssl-cnf] ?= "10"

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

do_install_append() {
    # We ship our own modified openssl configuration and as long as the
    # openssl-misc package is not installed within the same image we don't
    # get a conflict.
    install -d ${D}${sysconfdir}/ssl
    install -m 0644 ${S}/files/conf/ssl/openssl.cnf ${D}${sysconfdir}/ssl

    # because openssl expects it's default configuration file in libdir
    # we have to symlink it there together with some needed directories.
    install -d ${D}${libdir}/ssl
    ln -sf ${sysconfdir}/ssl/openssl.cnf ${D}${libdir}/ssl/openssl.cnf
    ln -sf ${sysconfdir}/ssl/certs ${D}${libdir}/ssl/certs
    ln -sf ${sysconfdir}/ssl/private ${D}${libdir}/ssl/private
}

FILES_${PN} += "${libdir}/ssl/"
