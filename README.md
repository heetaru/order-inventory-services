# Order & Inventory Services

Two Spring Boot services for orders and product stock. Order sends an event to Kafka; Catalog checks stock and sends a result back. Order then changes its status to `SUCCESS` or `REJECTED`.

Java 21, Spring Boot, PostgreSQL, Kafka, Spring Data JPA, MapStruct and Gradle. Each service has its own database. Flyway manages the Order database; Catalog uses Hibernate schema updates.

## Run locally

Requires Java 21 and Docker. Start the databases and Kafka:

```bash
docker compose up -d
```

Run each application in a separate terminal, or run its application class in IntelliJ IDEA:

```bash
./gradlew :catalog-service:bootRun
./gradlew :order-service:bootRun
```

Catalog runs on `localhost:8081`; Order runs on `localhost:8082`. PostgreSQL uses ports `5432` and `5433`, and Kafka uses `9092`. Stop other applications using these ports first.

## Try it with Postman

Send JSON with `Content-Type: application/json`:

1. `POST http://localhost:8081/api/v1/products`
   ```json
   {"name":"Keyboard","description":"Demo product","price":15,"stockQuantity":5}
   ```
2. Use the returned product ID in `POST http://localhost:8082/api/v1/orders`:
   ```json
   {"productId":1,"quantity":2}
   ```
3. `GET http://localhost:8082/api/v1/orders` initially shows `CREATED`, then `SUCCESS` after Kafka processing. `GET http://localhost:8081/api/v1/products/1` shows stock `3`. Replace `1` with your product ID.
4. An order for quantity `10` becomes `REJECTED`; stock stays `3`.

This is a learning project. Business tests, concurrent stock updates and recovery from failed event publishing need further work. `./gradlew test` currently runs the Catalog context test and requires the supporting services.

Stop the supporting services with `docker compose down`.
