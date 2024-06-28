package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class MovieTest {

    String baseUrl = "https://api.themoviedb.org/3";
    String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYzgwZjdmNjE2MmVmYWVhOWI5NzRkNmQ1MDVhYmJjYiIsIm5iZiI6MTcxOTQwODgxMC40OTEwNDQsInN1YiI6IjYyNWU0MTY5MzNhMzc2MDA1MGI1ZWYzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.grkl501TXVk3FjtIidZiSOxy-bJ9bZ6xwvzXpzVWWjE";

    @Test
    public void testMovieNowPlaying(){
        String endpoint = baseUrl+"/movie/now_playing?language=en-US&page=1";
        given()
                .header("Authorization",token)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("results.title[0]", equalTo("Inside Out 2"));
    }

    @Test
    public void testMoviePopular(){
        String endpoint = baseUrl+"/movie/popular?language=en-US&page=1";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization",token);

        Response response = request.get(endpoint);
        Assert.assertEquals(response.getStatusCode(),200);
        String titleMovie = response.getBody().jsonPath().getString("results.title[0]");
        Assert.assertEquals(titleMovie,"Inside Out 2");
    }

}
