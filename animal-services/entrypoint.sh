#!/bin/bash

# Print a starting message
echo "Starting animal services in Application..."

# Debugging: Check if the secrets directory exists
if [ ! -d "/run/secrets" ]; then
  echo "ERROR: /run/secrets directory not found."
  # List root directory to help with debugging
  ls /
  exit 1
fi

# Load MySQL username secret
if [ -f "/run/secrets/mysql_username" ]; then
  export MYSQL_USERNAME=$(cat /run/secrets/mysql_username)
  echo "Loaded MYSQL_USERNAME: $MYSQL_USERNAME"
else
  echo "ERROR: MySQL username secret not found at /run/secrets/mysql_username"
  # List contents of the secrets directory to debug
  ls -l /run/secrets
  exit 1
fi

# Load MySQL password secret
if [ -f "/run/secrets/mysql_password" ]; then
  export MYSQL_PASSWORD=$(cat /run/secrets/mysql_password)
  echo "Loaded MYSQL_PASSWORD."
else
  echo "ERROR: MySQL password secret not found at /run/secrets/mysql_password"
  # List contents of the secrets directory to debug
  ls -l /run/secrets
  exit 1
fi

# Start the application
exec java -jar /app/app.jar --spring.profiles.active=dev
