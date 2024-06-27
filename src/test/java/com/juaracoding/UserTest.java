package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    String baseUrl = "https://reqres.in/api";

    String token;

    @Test
    public void testUserList(){
        String endpoint = baseUrl+"/users?page=2";
        Response response = RestAssured.get(endpoint);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
        Assert.assertEquals(response.getStatusCode(),200);
        String firstName = response.getBody().jsonPath().getString("data.first_name[0]");
        Assert.assertEquals(firstName,"Michael");
    }

    @Test
    public void testSingleUser(){
        String endpoint = baseUrl+"/users/2";
        given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("data.id",equalTo(2));
    }

    @Test
    public void testUserNotFound(){
        String endpoint = baseUrl+"/users/23";
        given()
                .get(endpoint)
                .then()
                .statusCode(404);
    }

    @Test
    public void testAddUser(){
        String endpoint = baseUrl+"/users";
        JSONObject request = new JSONObject();
        request.put("name", "John");
        request.put("job", "QA Engineer");
        System.out.println(request.toJSONString());

        given()
                .header("content-type","application/json")
                .body(request.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .statusCode(201)
                .body("name",equalTo("John"));
    }

    @Test
    public void testEditUser(){
        String endpoint = baseUrl+"/users/2";
        JSONObject request = new JSONObject();
        request.put("name", "Nisa");
        request.put("job", "QA Engineer");
        System.out.println(request.toJSONString());

        given()
                .header("content-type","application/json")
                .body(request.toJSONString())
                .when()
                .put(endpoint)
                .then()
                .statusCode(200)
                .body("name",equalTo("Nisa"));
    }

    @Test(priority = 2)
    public void testDeleteUser(){
        System.out.println(token);
        String endpoint = baseUrl+"/users/2";
        given()
                .header("Authorization",token)
                .when()
                .delete(endpoint)
                .then()
                .statusCode(204);
    }

    @Test(priority = 1)
    public void testLoginUserValid(){
        String endpoint = baseUrl+"/login";

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");
        System.out.println(requestBody.toJSONString());

        RequestSpecification request = RestAssured.given();
        request.header("content-type","application/json");
        request.body(requestBody.toJSONString());

        Response response = request.post(endpoint);
        Assert.assertEquals(response.getStatusCode(),200);
        token = response.getBody().jsonPath().getString("token");
        System.out.println(token);
        Assert.assertNotNull(token);
    }

    @Test
    public void testLoginUserInvalid(){
        String endpoint = baseUrl+"/login";

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "eve.holt@reqres.inn");
        requestBody.put("password", "cityslicka");
        System.out.println(requestBody.toJSONString());

        RequestSpecification request = RestAssured.given();
        request.header("content-type","application/json");
        request.body(requestBody.toJSONString());

        Response response = request.post(endpoint);
        Assert.assertEquals(response.getStatusCode(),400);
        String error = response.getBody().jsonPath().getString("error");
        System.out.println(error);
        Assert.assertEquals(error,"user not found");
    }

}
