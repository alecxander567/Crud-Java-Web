#!/usr/bin/env bash

# Install Java 21 explicitly
sdk install java 21.0.3-tem
sdk use java 21.0.3-tem

# Set JAVA_HOME
export JAVA_HOME="$HOME/.sdkman/candidates/java/current"
export PATH="$JAVA_HOME/bin:$PATH"

# Run Maven build
./mvnw clean install
