package com.gitlabjiralink.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class ProjectResourceTest {

    @Test
    void testAddListUpdateDelete() {
        // add
        long id = 
            given()
                .contentType(ContentType.JSON)
                .body(new ProjectMapping(null, "gitlab", "jira"))
            .when()
                .post("/api/projects")
            .then()
                .statusCode(201)
                .extract().body().jsonPath().getLong("id");

        // list
        given()
            .when().get("/api/projects")
            .then().statusCode(200)
            .body("size()", is(1));

        // update
        given()
            .contentType(ContentType.JSON)
            .body(new ProjectMapping(id, "g", "j"))
        .when()
            .put("/api/projects/" + id)
        .then()
            .statusCode(200)
            .body("gitlabProject", equalTo("g"));

        // delete
        given()
            .when()
            .delete("/api/projects/" + id)
        .then()
            .statusCode(204);

        // not found
        given()
            .when()
            .delete("/api/projects/" + id)
        .then()
            .statusCode(404);
    }
}
