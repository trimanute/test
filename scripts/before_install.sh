#!/bin/bash
rm -f /opt/application/app.jar.old
if [ -f /opt/application/app.jar ]; then
    mv /opt/application/app.jar /opt/application/app.jar.old
fi 