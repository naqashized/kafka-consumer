# kafka-consumer
This gradle app has two sub projects using Springboot with Kafka. Producers app has a scheduler which produces methods after each minute.
And consumers app consumes the message published on the same topic name successfully. Both subprojects have Kafka integration using springboot kafka starter dependency.

## Running the app locally
There is docker compose file which can be used to run Kafka, Zookeeper(to manage kafka clusters) and Postgres database.
Run the Kafka using docker compose command;

```shell script
docker-compose up -d
```

Building the application:

```shell script
./gradlew build
```
Run the app using below command, the app expects to run local profile:

```shell script
 ./gradlew bootRun --args=--spring.profiles.active=local
```