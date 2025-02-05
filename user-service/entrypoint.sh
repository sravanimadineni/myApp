#!/bin/bash

# Print a starting message
echo "Starting user services in Application..."

# Debugging: Check if the secrets directory exists
if [ ! -d "/run/secrets" ]; then
  echo "ERROR: /run/secrets directory not found."
  ls /
  exit 1
fi

# Load MySQL username secret
if [ -f "/run/secrets/mysql_username" ]; then
  export DB_USERNAME=$(cat /run/secrets/mysql_username)
  echo "Loaded DB_USERNAME."
else
  echo "ERROR: MySQL username secret not found at /run/secrets/mysql_username"
  ls -l /run/secrets
  exit 1
fi

# Load MySQL password secret
if [ -f "/run/secrets/mysql_password" ]; then
  export DB_PASSWORD_FILE=$(cat /run/secrets/mysql_password)
  echo "Loaded MYSQL_PASSWORD."
else
  echo "ERROR: MySQL password secret not found at /run/secrets/mysql_password"
  ls -l /run/secrets
  exit 1
fi

# Start the application
exec java -jar /app/app.jar --spring.profiles.active=dev
