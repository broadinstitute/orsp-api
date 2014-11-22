from dockerfile/java:oracle-java8
maintainer Gregory Rushton <grushton@broadinstitute.org>

# Install Git
run apt-get install -y git

# Clone project
run git clone https://github.com/broadinstitute/orsp-api.git

run cd orsp-api
run ./gradlew shadowJar

# Expose the http port
expose 8181
expose 8282

workdir orsp-api

# cmd ["java", "-jar", "build/lib/orsp-api-all.jar", "server", "src/main/resources/appconfig.yml"]
