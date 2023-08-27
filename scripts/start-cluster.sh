  #!/bin/bash

set -e
set -x

. ./set-env.sh

minikube start -p $CLUSTER1_NAME --memory='1000mb' --cpus=1 --disk-size=20g --vm-driver="virtualbox" --insecure-registry=localhost:5000
minikube profile $CLUSTER1_NAME
minikube addons enable ingress
minikube addons enable metrics-server

eval $(minikube docker-env)

kubectl config use-context $CLUSTER1_NAME
minikube -p $CLUSTER1_NAME tunnel dashboard