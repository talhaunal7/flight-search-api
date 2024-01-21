# flight-search-api

## Technologies Used

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)

## Run Locally

Clone and run project with docker compose.

```bash
  $ docker-compose up
```

Application will run on [http://localhost:8097](http://localhost:8097)

## Example Requests

To send a request, you need to sign up choosing one of the `USER` and `ADMIN` roles.
<br>
<br>
After signing up, you have to add `Authorization` header as `Bearer your-token` to make request on;
<br>
-<ins>CRUD</ins> operation if you are an `ADMIN`
<br>
-<ins>Search flight</ins> operation if you are a `USER` or `ADMIN`

| Action         | localhost:8097/         | Example Request                                                    | 
|----------------|-------------------------|--------------------------------------------------------------------| 
| create airport | (POST) v1/airports      | `{"city" : "izmir"}`                                               | 
| get airport    | (GET) v1/airports/{id}  | `v1/airports/1`                                                    | 
| sign up        | (POST) v1/auth/signup   | `{"username": "john","password": "123456","role": "ADMIN"}`        | 
| search flights | (GET) v1/flights/search | ` v1/flights/search?departureAirportId=1&departureDate=2024-03-18` | 

## Documentation

[Swagger UI](http://localhost:8097/swagger-ui/index.html) `http://localhost:8097/swagger-ui/index.html`- Documentation &
Testing
<br>
<br>

![](https://i.imgur.com/iCzgVhX.jpg)