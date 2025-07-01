package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        RestAssured.given()
            .when().get("/hello")
            .then()
               .statusCode(200)
               .body(is("Hello from Quarkus"));
    }
}
