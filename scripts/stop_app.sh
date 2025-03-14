#!/bin/bash
pid=$(pgrep -f app.jar)
if [ -n "$pid" ]; then
    kill $pid
fi 