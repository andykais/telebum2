#!/bin/sh


if [ -z "$1" ]; then
  echo "usage: $0 [service_name]"
  echo
  echo "Available Services:"
  docker-compose -f ./compose/docker-compose.yml config --services | while read line; do
    if docker ps | grep $line 2>&1>/dev/null; then
      echo $line
    fi
  done
  exit 1
fi


source ./compose/variables.env
CONTAINER_NAME=$PROJECT_NAME_$1

CONTAINER_ID=$(docker ps | grep $CONTAINER_NAME | awk '{print $1}')

if [ -z "$CONTAINER_ID" ]; then
  echo "No docker containers running for this project"
  exit 1
fi

echo "docker exec -it $CONTAINER_ID bash"
docker exec -it $CONTAINER_ID bash
