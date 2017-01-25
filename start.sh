#!/bin/bash

docker_dev() {
  docker-compose \
    -f ./compose/docker-compose.yml \
    -f ./compose/docker-compose.dev.yml \
    -p "telebumbo_app" \
    "$@"
}

set -a

case $1 in
  dev)
    source ./compose/variables.env
    docker_dev up
    ;;
  prod)
    docker_prod up
    ;;
  *)
    echo "usage: $0 [ dev | prod ]"
    ;;
esac
