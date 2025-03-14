#!/bin/bash
cd /opt/application
export WEATHER_API_KEY="${WEATHER_API_KEY}"
nohup java -jar app.jar > /opt/application/application.log 2>&1 & 