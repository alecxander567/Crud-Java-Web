#!/usr/bin/env bash

# Install SDKMAN to manage Java versions
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Java (pick version you need)
sdk install java 17.0.8-tem
sdk use java 17.0.8-tem

# Set JAVA_HOME and PATH for Maven
export JAVA_HOME="$HOME/.sdkman/candidates/java/current"
export PATH="$JAVA_HOME/bin:$PATH"

# Run the build
#!/usr/bin/env bash
./mvnw clean install -DskipTests

