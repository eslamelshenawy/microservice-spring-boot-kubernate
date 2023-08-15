#!/bin/bash

set -x

.  ./set-env.sh

kubectl config set-context $CLUSTER1_NAME
kubectl config use-context $CLUSTER1_NAME

kubectl delete  deployment mysql
kubectl delete  service mysql
kubectl delete  configmap mysql
kubectl delete  secret mysql
