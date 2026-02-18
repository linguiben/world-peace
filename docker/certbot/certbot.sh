#!/usr/bin/env bash
set -euo pipefail

# Usage:
#   ./certbot.sh                      # default domains
#   DOMAINS='joveso.com www.joveso.com occ.joveso.com' ./certbot.sh
#   ./certbot.sh occ.joveso.com        # add/renew specific domain(s)
#
# Notes:
# - Uses webroot under /opt/docker/nginx/certbot/www (served by nginx container).
# - Writes certs under /etc/letsencrypt (mounted into nginx container read-only).

WEBROOT_HOST_DIR="/opt/docker/nginx/certbot/www"
LETSENCRYPT_DIR="/etc/letsencrypt"
EMAIL="linguiben@163.com"

DEFAULT_DOMAINS=(joveso.com www.joveso.com)

# Allow overriding domains via env or args.
if [[ -n "${DOMAINS:-}" ]]; then
  # shellcheck disable=SC2206
  DOMAINS_ARR=(${DOMAINS})
elif [[ $# -gt 0 ]]; then
  DOMAINS_ARR=("$@")
else
  DOMAINS_ARR=("${DEFAULT_DOMAINS[@]}")
fi

# Build -d args
D_ARGS=()
for d in "${DOMAINS_ARR[@]}"; do
  D_ARGS+=( -d "$d" )
done

echo "Requesting/renewing certs for: ${DOMAINS_ARR[*]}"

docker run --rm -it \
  -v "${WEBROOT_HOST_DIR}:/var/www/certbot" \
  -v "${LETSENCRYPT_DIR}:/etc/letsencrypt" \
  certbot/certbot certonly \
  --webroot -w /var/www/certbot \
  --email "${EMAIL}" \
  --agree-tos --no-eff-email \
  --force-renewal \
  "${D_ARGS[@]}"

# Check the certificate
#openssl x509 -in /etc/letsencrypt/live/jupiterso.com-0001/fullchain.pem -noout -issuer -subject -dates

# Test renewal process, set cron job to run this command 90 days later
#15 3 * * * docker run --rm -v /opt/docker/nginx/certbot/www:/var/www/certbot -v /etc/letsencrypt:/etc/letsencrypt certbot/certbot renew --quiet --deploy-hook "docker exec nginx nginx -s reload"
#docker run --rm -it \
#  -v /opt/docker/nginx/certbot/www:/var/www/certbot \
#  -v /etc/letsencrypt:/etc/letsencrypt \
#  certbot/certbot renew --dry-run
