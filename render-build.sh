#!/usr/bin/env bash

# Install SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Java
sdk install java 17.0.8-tem
sdk use java 17.0.8-tem

# Set JAVA_HOME for Maven wrapper
export JAVA_HOME="$HOME/.sdkman/candidates/java/current"
export PATH="$JAVA_HOME/bin:$PATH"

# Now build your Java project
./mvnw clean install
