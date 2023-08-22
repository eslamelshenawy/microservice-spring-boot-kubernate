#!/bin/bash

set -e
set -x

. ./set-env.sh

cd ..

minikube profile $CLUSTER1_NAME

# make Kubernetes reusing Docker daemon
# https://kubernetes.io/docs/setup/minikube/#reusing-the-docker-daemon
eval $(minikube docker-env)
docker images

mvn clean
mvn package

cd selfServiceGateway
docker build -t vmware/gateway:1.1 .
cd ..

cd selfService
mvn clean
mvn package
docker build -t vmware/selfservice:1.1 .
cd ..
cd dataService
mvn clean
mvn package
docker build -t vmware/db:1.1 .
cd ..

docker images

cd scripts