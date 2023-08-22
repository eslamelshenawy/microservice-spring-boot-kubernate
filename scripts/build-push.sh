#!/bin/bash

set -x

REPO_USER=${1:-andriykalashnykov}
IS_DEBUG_VERSION=${2:-true}
APP_VER=${3:-1.2}

DEBUG_IMAGE_SUFFIX=""
DEBUG_DOCKERFILE_NAME="Dockerfile"

CURRENT_DIR=`pwd`
SCRIPT_DIR=$(dirname $0)

if [ "$IS_DEBUG_VERSION" == "true" ]; then
    DEBUG_IMAGE_SUFFIX="-debug"
    DEBUG_DOCKERFILE_NAME="Dockerfile.debug"
fi

docker login

cd ../$SCRIPT_DIR/selfService
docker build -f ${DEBUG_DOCKERFILE_NAME} -t vmware/organization${DEBUG_IMAGE_SUFFIX}:$APP_VER .
docker tag vmware/organization${DEBUG_IMAGE_SUFFIX}:$APP_VER $REPO_USER/organization${DEBUG_IMAGE_SUFFIX}:$APP_VER
docker push $REPO_USER/organization${DEBUG_IMAGE_SUFFIX}:$APP_VER


cd ../$SCRIPT_DIR/dataService
docker build -f ${DEBUG_DOCKERFILE_NAME} -t vmware/db${DEBUG_IMAGE_SUFFIX}:$APP_VER .
docker tag vmware/db${DEBUG_IMAGE_SUFFIX}:$APP_VER $REPO_USER/db${DEBUG_IMAGE_SUFFIX}:$APP_VER
docker push $REPO_USER/db${DEBUG_IMAGE_SUFFIX}:$APP_VER

cd ../$SCRIPT_DIR/selfServiceGateway
docker build -f ${DEBUG_DOCKERFILE_NAME} -t vmware/gateway${DEBUG_IMAGE_SUFFIX}:$APP_VER .
docker tag vmware/gateway${DEBUG_IMAGE_SUFFIX}:$APP_VER $REPO_USER/gateway${DEBUG_IMAGE_SUFFIX}:$APP_VER
docker push $REPO_USER/gateway${DEBUG_IMAGE_SUFFIX}:$APP_VER

cd $CURRENT_DIR

docker images