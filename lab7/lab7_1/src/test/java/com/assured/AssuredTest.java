package com.assured;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;


public class AssuredTest {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }

    @Test
    void get_all_todos_Test(){
        RestAssured.given()
            .when()
            .get("/todos")
            .then()
            .statusCode(200);
    }   

    @Test
    void get_id_todos_Test(){
        RestAssured.given()
            .when()
            .get("/todos/4")
            .then()
            .assertThat()
            .body("title", equalTo("et porro tempora"));
    }


    @Test
    void exists_todos_Test(){
        RestAssured.given()
            .when()
            .get("/todos")
            .then()
            .assertThat()
            .body("id", hasItems(198,199));
    }

    @Test
    void loading_time_Test(){
        RestAssured.given()
        .when()
        .get("/todos")
        .then()
        .time(lessThan(2000L));
    }

        

    
}
