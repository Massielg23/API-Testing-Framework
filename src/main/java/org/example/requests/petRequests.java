package org.example.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class petRequests {
    public Response postAddNewPetToStore(String requestBody){
        return RestAssured
                .given()
                    .header("accept", "application/xml")
                    .header("Content-Type","application/json")
                    .body(requestBody)
                .post();
    }

    public Response getPetById(Integer petId){
        return RestAssured
                .given()
                    .pathParam("petId", petId)
                    .header("accept", "application/xml")
                .get("/{petId}");
    }
}
