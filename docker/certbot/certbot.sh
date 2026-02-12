#!/usr/bin/env bash
set -euo pipefail

DOMAIN="sg.joveso.com"
EMAIL="linguiben@163.com"
WEBROOT_HOST="/opt/docker/nginx/certbot/www"
LE_HOST="/etc/letsencrypt"

mkdir -p "${WEBROOT_HOST}/.well-known/acme-challenge"

# Issue/renew certificate via HTTP-01 (webroot)
docker run --rm \
  -v "${WEBROOT_HOST}:/var/www/certbot" \
  -v "${LE_HOST}:/etc/letsencrypt" \
  certbot/certbot certonly \
  --webroot -w /var/www/certbot \
  --email "${EMAIL}" \
  --agree-tos --no-eff-email \
  --keep-until-expiring \
  -d "${DOMAIN}"

# Check the certificate (optional)
# openssl x509 -in "/etc/letsencrypt/live/${DOMAIN}/fullchain.pem" -noout -issuer -subject -dates

# Renewal example (cron/systemd):
# docker run --rm -v "${WEBROOT_HOST}:/var/www/certbot" -v "${LE_HOST}:/etc/letsencrypt" certbot/certbot renew --quiet --deploy-hook "docker exec nginx nginx -s reload"
