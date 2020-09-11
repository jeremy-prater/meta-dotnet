
do_install() {
    echo "Installing ${DESCRIPTION} ..."

    install -d ${D}/opt/dotnet
    tar -xvzf ${WORKDIR}/${SOURCE_FILE} -C ${D}/opt/dotnet

    # chown all the files since we don't know what user/group was provided
    chown -R -v root:root ${D}/opt/dotnet
}

INSANE_SKIP_${PN}_append += "already-stripped"
INSANE_SKIP_${PN}_append += "staticdev"
INSANE_SKIP_${PN}_append += "file-rdeps"

FILES_${PN} += "/opt/dotnet"

