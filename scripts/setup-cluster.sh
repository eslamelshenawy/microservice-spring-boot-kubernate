#!/bin/bash

set -x

. ./set-env.sh

kubectl config set-context $CLUSTER1_NAME
kubectl config use-context $CLUSTER1_NAME

#kubectl create namespace $NAMESPACE_GATEWAY
#kubectl create namespace $NAMESPACE_ORGANIZATION
#kubectl create namespace $NAMESPACE_USER
#kubectl create namespace $NAMESPACE_MONGO
#kubectl create namespace $NAMESPACE_MYSQL

# create clusterRole
kubectl apply  -f ../k8s/rbac-cluster-role.yaml

kubectl create rolebinding microservices-kubernetes-namespace-reader-binding --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=default:default



