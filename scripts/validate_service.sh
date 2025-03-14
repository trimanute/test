#!/bin/bash
sleep 30
pgrep -f app.jar
if [ $? -eq 0 ]; then
    echo "Application is running"
    exit 0
else
    echo "Application is not running"
    exit 1
fi 