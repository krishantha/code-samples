from java:8
expose 8080
expose 8192
add target/sample-microservice-1.0-SNAPSHOT.jar /opt/sample-microservice-1.0-SNAPSHOT.jar
workdir /opt
ENTRYPOINT exec java $JAVA_OPTS -jar sample-microservice-1.0-SNAPSHOT.jar