#!/bin/bash

IMAGE_REGISTRY=quay.io
IMAGE_PATH=rh_rh
APP_NAME=todo-java
APP_IMAGE_TAG=latest

docker build -t quay.io/${IMAGE_PATH}/${APP_NAME}:${APP_IMAGE_TAG} --platform linux/amd64 -f src/container/Containerfile .
docker push quay.io/${IMAGE_PATH}/${APP_NAME}:${APP_IMAGE_TAG}