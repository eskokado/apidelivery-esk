# apidelivery-esk

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Description

The created API has the following requirements:

- Create an item delivery.
- Search for delivery of a specific item.
- Search for deliveries by recipient or by sender.
- Update status as delivered.
- Cancel a delivery.
- Consume another existing REST API to fetch data, such as neighborhood, street and geolocation, from zip code and number entered.
- Authentication flow using bearer token.
- Documentation using Swagger.

This API allows you to manage item deliveries, allowing you to create, query and update the delivery status, as well as search for deliveries by recipient or sender. Furthermore, it consumes an external API to fetch additional information about delivery addresses.

API security is ensured by an authentication flow, which requires the use of a valid authentication token to access protected resources.

API documentation is provided by Swagger, making it easy to use and understand the features available in the API.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/apidelivery-esk-1.0-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing JAX-RS and more

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
