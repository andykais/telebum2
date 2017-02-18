#!/bin/bash

cd "$(dirname "$0")"

set -a
source ./compose/variables.env

docker_dev() {
  docker-compose \
    -f ./compose/docker-compose.yml \
    -f ./compose/docker-compose.dev.yml \
    -p $PROJECT_NAME \
    "$@"
}

case $1 in
  dev)
    docker_dev up $2
    ;;
  prod)
    docker_prod up
    ;;
  *)
    echo "usage: $0 [ dev | prod ]"
    ;;
esac
