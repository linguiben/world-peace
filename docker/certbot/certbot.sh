docker run --rm -it \
  -v /opt/docker/nginx/certbot/www:/var/www/certbot \
  -v /etc/letsencrypt:/etc/letsencrypt \
  certbot/certbot certonly \
  --webroot -w /var/www/certbot \
  --email linguiben@163.com \
  --agree-tos --no-eff-email \
  --force-renewal \
  -d jupiterso.com -d www.jupiterso.com

# Check the certificate
#openssl x509 -in /etc/letsencrypt/live/jupiterso.com-0001/fullchain.pem -noout -issuer -subject -dates

# Test renewal process, set cron job to run this command 90 days later
#15 3 * * * docker run --rm -v /opt/docker/nginx/certbot/www:/var/www/certbot -v /etc/letsencrypt:/etc/letsencrypt certbot/certbot renew --quiet --deploy-hook "docker exec nginx nginx -s reload"
#docker run --rm -it \
#  -v /opt/docker/nginx/certbot/www:/var/www/certbot \
#  -v /etc/letsencrypt:/etc/letsencrypt \
#  certbot/certbot renew --dry-run
