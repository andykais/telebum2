#!/bin/bash

set -a
source ./compose/variables.env

docker_init() {
  docker-compose \
    -f ./compose/docker-compose.yml \
    -f ./compose/docker-compose.init.yml \
    -p $PROJECT_NAME \
    "$@"
}

docker_init build
[[ $? != 0 ]] && exit 1 # exits if build fails
docker_init up web-api
docker_init up web-client
docker_init stop postgres
