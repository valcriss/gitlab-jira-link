package com.gitlabjiralink.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class LogResourceTest {

    @Test
    void testListEmpty() {
        given()
            .when().get("/api/logs")
            .then().statusCode(200)
            .body("size()", is(0));
    }
}
