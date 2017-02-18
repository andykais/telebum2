#!/bin/sh

cd "$(dirname "$0")"
# should stop the script if something fails
set -e


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


set -a
source ./compose/variables.env
CONTAINER_NAME=${PROJECT_NAME}_$1

CONTAINER_ID=$(docker ps | grep $CONTAINER_NAME | head -1 | awk '{print $1}')

if [ -z "$CONTAINER_ID" ]; then
  CONTAINER_ID=$(docker ps -a | grep $CONTAINER_NAME | awk '{print $1}')
  if [ -z "$CONTAINER_ID" ]; then
    echo "no docker container exists for this project named $CONTAINER_ID"
    exit 1
  else
    #echo $CONTAINER_ID
    #docker run -it $CONTAINER_ID bash
    set -o xtrace
    docker-compose \
      -f ./compose/docker-compose.yml \
      -f ./compose/docker-compose.dev.yml \
      -f ./compose/docker-compose.tty.yml \
      run --no-deps --service-ports $1 bash
  fi
else
  set -o xtrace
  docker exec -it $CONTAINER_ID bash
fi

