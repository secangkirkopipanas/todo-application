#!/bin/bash

IMAGE_REGISTRY=quay.io
IMAGE_PATH=rh_rh
APP_NAME=todo-java
APP_IMAGE_TAG=latest

PLATFORM=linux/amd64

# Build container image
podman build -t ${IMAGE_REGISTRY}/${IMAGE_PATH}/${APP_NAME}:${APP_IMAGE_TAG} --platform ${PLATFORM} -f src/container/Containerfile .

# Push container image to registry
podman push ${IMAGE_REGISTRY}/${IMAGE_PATH}/${APP_NAME}:${APP_IMAGE_TAG}