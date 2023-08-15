#!/bin/bash

# set -e
set -x

. ./set-env.sh

cd ../k8s

kubectl config use-context $CLUSTER1_NAME

kubectl apply  -f mysql-configmap.yaml
kubectl apply  -f mysql-secret.yaml
kubectl apply  -f mysql-deployment.yaml

cd ../scripts