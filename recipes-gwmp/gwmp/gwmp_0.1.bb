
DESCRIPTION = "Gateway MultiProtocol PFE Project"
LICENSE = "CLOSED"
inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"




SRC_URI = "git://github.com/AzizElghali/PFE-2022.git;protocol=https;branch=main"
SRCREV = "99d94be0066b76b177fe48183fdccd45b4dc3f7d"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "\
    python3-grpcio \
    python3-paho-mqtt \
    python3-protobuf \
    pymodbus \
    python3-pickle \
    python3-pyserial \
    bash \
"

do_install() {
    # Install directories
    install -d ${D}/opt
    install -d ${D}${systemd_unitdir}/system/

    # Install files
    install -m 0644 ${S}/Services/config-managerd.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/Services/modbusd.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/Services/mqttd.service ${D}${systemd_unitdir}/system

    cp -r ${S}/* ${D}/opt
}

FILES_${PN} = "/opt/* /etc/* ${systemd_unitdir}/*"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "config-managerd.service mqttd.service modbusd.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"
