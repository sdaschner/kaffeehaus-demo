ARG BUILD_DIR="/kaffeehaus"

FROM maven:3.8.3-openjdk-16 AS builder

ARG BUILD_DIR

COPY src/ "${BUILD_DIR}/src/" 
COPY pom.xml "${BUILD_DIR}/pom.xml"

WORKDIR "${BUILD_DIR}"

RUN mvn clean package


FROM adoptopenjdk/openjdk16-openj9:x86_64-alpine-jre-16.0.1_9_openj9-0.26.0

ARG BUILD_DIR

RUN apk add curl

COPY --from=builder "${BUILD_DIR}"/target/quarkus-app/lib/ /deployments/lib/
COPY --from=builder "${BUILD_DIR}"/target/quarkus-app/*.jar /deployments/
COPY --from=builder "${BUILD_DIR}"/target/quarkus-app/quarkus/ /deployments/quarkus/
COPY --from=builder "${BUILD_DIR}"/target/quarkus-app/app/ /deployments/app/

CMD ["java", "-jar", "-Dquarkus.http.host=0.0.0.0", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager", "/deployments/quarkus-run.jar"]
