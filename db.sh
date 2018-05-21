#!/bin/bash

USER="root"
USER_PASSWORD="root"

DATABASE="magneto"
CONTAINER_NAME="magneto"

docker stop magneto
docker rm magneto

docker \
  run \
  --detach \
  --env POSTGRES_USER=${USER} \
  --env POSTGRES_PASSWORD=${USER_PASSWORD} \
  --env POSTGRES_DB=${DATABASE} \
  --name ${CONTAINER_NAME} \
  --publish 5432:5432 \
  postgres:9.4;

