package com.kodilla.ebooklibrary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class GetRequests {
    String baseUri = "https://ta-ebookrental-be.herokuapp.com";
    int userId = 41713;

    @Test
    public void getTitles() {

        String response = given().
                baseUri(baseUri).
                queryParam("userId", userId).
                when().
                get("/titles/").
                then().
                //log().all().
                        statusCode(200).
                contentType(ContentType.JSON).
                extract().response().jsonPath().prettify();

        JSONArray array = new JSONArray(response);
        Assert.assertEquals((array.getJSONObject(1).get("author")), "Ian Fleming");
    }

    @Test
    public void getTitlesUsingJsonTree() throws JsonMappingException, JsonProcessingException {

        String jsonArray = given().
                baseUri(baseUri).
                queryParam("userId", userId).
                when().
                get("/titles/").
                then().
                //log().all().
                        statusCode(200).
                contentType(ContentType.JSON).
                extract().response().jsonPath().prettify();

        // Creating an instance of ObjectMapper class
        ObjectMapper objectMapper = new ObjectMapper();
        // Get tree representation of json
        JsonNode jsonTree = objectMapper.readTree(jsonArray);
        Assert.assertEquals((jsonTree.get(2).get("author").asText()), "Ian Fleming");
//http://makeseleniumeasy.com/2020/09/17/rest-assured-tutorial-47-fetch-value-from-nested-json-array-using-jsonnode-jackson-at-method/
//http://makeseleniumeasy.com/2020/09/16/rest-assured-tutorial-46-fetch-value-from-json-array-using-jsonnode-jackson-get-path-methods/
    }

    @Test
    public void getItems() { //lista i dostępność danego tytułu
        String response = given().
                baseUri(baseUri).
                queryParam("titleId", 41718).
                queryParam("userId", userId).
                when().
                get("/items/").
                then().
                log().all().
                statusCode(200).
                contentType(ContentType.JSON).
                extract().response().jsonPath().prettify();

        JSONArray items = new JSONArray(response);
        Assert.assertEquals((items.getJSONObject(0).get("status")), "AVAILABLE");
    }

    @Test
    public void getItemsWithFetchItemId() { //lista i dostępność danego tytułu
        String fetchId = given().
                baseUri(baseUri).
                queryParam("userId", userId).
                when().
                get("/titles/").
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                extract().response().jsonPath().prettify();

        JSONArray array = new JSONArray(fetchId);
        int titleId = array.getJSONObject(1).getInt("id");

        String response = given().
                baseUri(baseUri).
                queryParam("titleId", titleId).
                queryParam("userId", userId).
                when().
                get("/items/").
                then().
                //log().all().
                        statusCode(200).
                contentType(ContentType.JSON).
                extract().response().jsonPath().prettify();

        JSONArray items = new JSONArray(response);
        Assert.assertEquals((items.getJSONObject(0).get("status")), "AVAILABLE");
    }

    @Test
    public void getRents() { //szczegóły egzemplarza danego tytułu kto i kiedy wypozyczył
        String response = given().
                baseUri(baseUri).
                queryParam("userId", userId).
                queryParam("itemId", 41728).
                when().
                get("/rents/").
                then().
                log().all().
                statusCode(200).
                contentType(ContentType.JSON).
                extract().response().jsonPath().prettify();

        JSONArray items = new JSONArray(response);
        Assert.assertEquals((items.getJSONObject(0).get("customerName")), "John");
    }
}
