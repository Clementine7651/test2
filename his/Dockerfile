FROM openjdk:17-jdk-alpine  as base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM  gradle:jdk17-alpine AS build
WORKDIR /app
COPY . /app
RUN gradle -b build.gradle -x test clean build --info

FROM base  as final
COPY --from=build /app/build/libs .
COPY --from=build /app/build/resources/main .
ENTRYPOINT ["java", "-jar", "/app/his-0.0.1-SNAPSHOT.jar"]