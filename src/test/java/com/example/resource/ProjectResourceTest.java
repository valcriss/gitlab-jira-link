package com.example.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ProjectResourceTest {

    @Test
    public void testListEmpty() {
        RestAssured.given()
            .when().get("/api/projects")
            .then()
            .statusCode(200)
            .body("size()", Matchers.is(0));
    }

    @Test
    public void testAddProject() {
        RestAssured.given()
            .contentType("application/json")
            .body("{\"gitlab_project_id\":\"1\", \"jira_project_key\":\"ABC\"}")
            .when().post("/api/projects")
            .then()
            .statusCode(200)
            .body("gitlab_project_id", Matchers.is("1"))
            .body("jira_project_key", Matchers.is("ABC"));

        RestAssured.given()
            .when().get("/api/projects")
            .then()
            .statusCode(200)
            .body("size()", Matchers.is(1));
    }
}
