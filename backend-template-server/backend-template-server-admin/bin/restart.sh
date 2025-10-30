#!/usr/bin/env bash

APP_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd | sed 's/\/bin//')


#Profile env
if [[ -n "$1" ]]; then
  PROFILE=$1
else
  PROFILE="dev"
fi

echo "Stopping service..."
${APP_DIR}/bin/shutdown.sh ${PROFILE}


if [ $? -eq 0 ]; then
    echo "Service stopped successfully."
else
    echo "Failed to stop service. Exiting..."
    exit 1
fi

echo "Starting service..."
${APP_DIR}/bin/startup.sh ${PROFILE}


if [ $? -eq 0 ]; then
    echo "Service started successfully."
else
    echo "Failed to start service. Exiting..."
    exit 1
fi

echo "Restart completed successfully."