# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS portability layer - library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "webos/libs"

# nyx-lib needs nyx-modules at runtime, but a runtime dependency is not defined
# here because nyx-modules is MACHINE_ARCH (e.g. qemux86), while nyx-lib is
# TUNE_PKGARCH  (e.g. i586). Instead, it is pulled into the image by adding it to
# the RDPENDS_${PN} of packagegroup-webos-extended. Putting
#   RDEPENDS_${PN} = "nyx-modules"
# here would cause bitbake to re-execute the do_package task for each MACHINE,
# even if these MACHINE-s were all i586 and should therefore share the same nyx-lib
# .ipk and sstate files. (The reason do_package is re-executed when a component
# in any of the R* variables is re-built is because its package name is stored in
# this component's .ipk and it may have changed because debian.bbclass is inherited.)

DEPENDS = "glib-2.0"

PV = "6.1.0-70+git${SRCPV}"
SRCREV = "6e1d0ab26597904722488ebeebfc4a0b4e570c2e"

inherit webos_ports_fork_repo
inherit webos_cmake
inherit pkgconfig

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

# Patch to support additional async suspend API's
SRC_URI += "file://0001-Implement-asynchronous-suspend-resume-methods-for-sy.patch"
