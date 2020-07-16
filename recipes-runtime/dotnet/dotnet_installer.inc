
do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}/opt/dotnet
    tar -xvzf ${WORKDIR}/${SOURCE_FILE} -C ${D}/opt/dotnet
}

INSANE_SKIP_${PN}_append += "already-stripped"
INSANE_SKIP_${PN}_append += "staticdev"
INSANE_SKIP_${PN}_append += "file-rdeps"

FILES_${PN} += "/opt/dotnet"

