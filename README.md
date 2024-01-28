# LocaLoge-Kubernetes
This is a website project allowing you to rent properties.

## Architecture
The front part of the project uses **NodeJS** and **React**, while the back is made up of 3 microservices developed in **Java** (version 17) with **SpringBoot**, and a **MySQL** database microservice.

### Kubernetes (k8s)
The project was developed with the aim of being deployed on a cloud. \
The services are therefore containerized using **Docker**, and orchestrated by **Kubernetes**.

### Istio
Network gateway management is carried out using the **Istio** service mesh.

## Prerequisites
- Install NodeJS
- Install Docker
- Install Minikube
- Install Istio

## How to run the project
1. Launch **Docker**
2. Launch **Minikube** and open the tunnel
```
> minikube start
> minikube tunnel
```
3. Apply the **Kubernetes** files that you can find in the [server](https://github.com/AurelDP/LocaLoge-Kubernetes/tree/main/server) folder
```
> cd server
> kubectl apply -f services.yaml
> kubectl apply -f deployments.yaml
> kubectl apply -f ingress.yaml
```
4. Wait for the pods to be ready
5. Run the website interface
```
> cd client
> npm install
> npm run dev
```
