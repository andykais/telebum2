#!/bin/sh

wait-for-it.sh web-client:3000
wait-for-it.sh web-api:3001

envsubst '$WEB_CLIENT_PORT $WEB_API_PORT' < /etc/nginx/nginx.conf.tmp > /etc/nginx/nginx.conf

exec nginx -g 'daemon off;' "$@"
