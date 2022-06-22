package com.kodilla.ebooklibrary;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequests {
    String baseUri = "https://ta-ebookrental-be.herokuapp.com";
    int userId = 41713;

    @Test
    public void createTitle() {
        String payload = "{\n" +
                "        \"userId\": 41713,\n" +
                "        \"author\": \"Ian Fleming\",\n" +
                "        \"title\": \"The Man with the Golden Gun\",\n" +
                "        \"year\": 1965\n" +
                "    }";
        given().
                body(payload).
                contentType(ContentType.JSON).
                baseUri(baseUri).
                when().
                queryParam("userId", userId).
                post("/titles/").
                then().
                log().all().
                statusCode(200).
                contentType(ContentType.JSON);
    }
    //https://ta-ebookrental-be.herokuapp.com/titles/?userId=41713
}
