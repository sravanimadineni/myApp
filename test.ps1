# Define the secrets directory
$SecretsDir = "C:\Users\Sravani Madineni\OneDrive\Documents\myproject\whatsapp-like-app\secrets"

# Check if secrets directory exists
if (-Not (Test-Path $SecretsDir)) {
    Write-Host "ERROR: Secrets directory not found at $SecretsDir."
    exit 1
}

# Load MySQL secrets
if (Test-Path "$SecretsDir\mysql_username") {
    $MYSQL_USERNAME = Get-Content "$SecretsDir\mysql_username"
    Write-Host "Loaded MYSQL_USERNAME: $MYSQL_USERNAME"
} else {
    Write-Host "ERROR: MySQL username secret not found at $SecretsDir\mysql_username"
    exit 1
}

if (Test-Path "$SecretsDir\mysql_password") {
    $MYSQL_PASSWORD = Get-Content "$SecretsDir\mysql_password"
    Write-Host "Loaded MYSQL_PASSWORD."
} else {
    Write-Host "ERROR: MySQL password secret not found at $SecretsDir\mysql_password"
    exit 1
}

# Start the application
Write-Host "Starting the application..."
Start-Process "java" -ArgumentList "-jar app.jar --spring.profiles.active=dev"
