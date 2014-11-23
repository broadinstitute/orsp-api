FROM debian:jessie
FROM dockerfile/java:oracle-java8

maintainer Gregory Rushton <grushton@broadinstitute.org>

# Install Git
run apt-get install -y git

# Clone project
run git clone https://github.com/broadinstitute/orsp-api.git .

run git pull

run ./gradlew shadowJar

# Expose the http ports
expose 8181
expose 8282

run ls -al build/libs

cmd ["java", "-jar", "build/libs/data-all.jar", "server", "src/main/resources/appconfig.yml"]
