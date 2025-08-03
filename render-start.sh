#!/usr/bin/env bash

# Install SDKMAN to manage Java versions
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Java
sdk install java 17.0.8-tem
sdk use java 17.0.8-tem

# Set JAVA_HOME
export JAVA_HOME="$HOME/.sdkman/candidates/java/current"
export PATH="$JAVA_HOME/bin:$PATH"

# Start Spring Boot
./mvnw clean package -DskipTests

