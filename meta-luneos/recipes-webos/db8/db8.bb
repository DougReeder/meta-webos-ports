# Copyright (c) 2013-2014 LG Electronics, Inc.

SUMMARY = "A userspace service that provides access to the Open webOS database"
SECTION = "webos/base"
AUTHOR = "Maksym Sditanov <maxim.sditanov@lge.com>"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# db8 is also the provider for mojodb
PROVIDES = "mojodb"

DEPENDS = "luna-service2 jemalloc icu pmloglib curl glib-2.0 leveldb leveldb-tl boost"

PV = "3.2.0-145+git${SRCPV}"
SRCREV = "0f18ab88053227608a8729ac54fb2bdb0a8dc363"

RDEPENDS_${PN} += "leveldb bash"
RDEPENDS_${PN}-tests += "bash"

inherit webos_ports_fork_repo
inherit webos_cmake
inherit webos_system_bus
inherit pkgconfig
inherit pkgconfig
inherit webos_system_bus

EXTRA_OECMAKE += "-DWEBOS_CONFIG_BUILD_TESTS:BOOL=TRUE -DWEBOS_DB8_BACKEND:STRING='leveldb;sandwich' -DCMAKE_SKIP_RPATH:BOOL=TRUE"

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

PACKAGES =+ "${PN}-tests"

FILES_${PN}-tests = "${libdir}/${PN}/tests/*"
FILES_${PN}-dbg += "${libdir}/${PN}/tests/.debug"

SRC_URI += "file://0001-Add-com.palm.mediapermissions-service-as-admin-for-m.patch"

CXXFLAGS += "-fpermissive"
