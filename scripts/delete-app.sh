#!/bin/bash

set -x

. ./set-env.sh

kubectl config set-context $CLUSTER1_NAME
kubectl config use-context $CLUSTER1_NAME

kubectl delete pods --all
kubectl delete deployment --all
kubectl delete services --all







