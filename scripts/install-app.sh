#!/bin/bash

# set -e
set -x

. ./set-env.sh


#minikube addons enable ingress

cd ../k8s

kubectl config use-context $CLUSTER1_NAME

kubectl apply  -f selfService-deployment.yaml
kubectl apply  -f gateway-deployment.yaml
kubectl apply  -f db-service-deployment.yaml
kubectl apply  -f ingress.yaml

# set Minikupe IP for microservices-cluster.info in /etc/hosts
minikube profile $CLUSTER1_NAME
CLUSTER1_IP=$(minikube ip)
echo $CLUSTER1_IP
sudo sed -i.bak 's/.*microservices-cluster.info/'"$CLUSTER1_IP"' microservices-cluster.info/' /etc/hosts && sudo rm /etc/hosts.bak
echo "$(minikube ip) microservices-cluster.info" | sudo tee -a /etc/hosts

cd ../scripts
