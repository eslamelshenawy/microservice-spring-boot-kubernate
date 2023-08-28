  #!/bin/bash

set -e
set -x

. ./set-env.sh

minikube start -p $CLUSTER1_NAME --memory='3900mb' --cpus=2 --disk-size=20g --vm-driver="docker" --insecure-registry=localhost:5000 --force
minikube profile $CLUSTER1_NAME
minikube addons enable ingress
minikube addons enable metrics-server

eval $(minikube docker-env)

kubectl config use-context $CLUSTER1_NAME
minikube -p $CLUSTER1_NAME tunnel dashboard