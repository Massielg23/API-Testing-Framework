package org.example.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class storeRequests {

    public Response postNewOrder(String requestBody){
        return RestAssured
                .given()
                    .header("accept","application/json")
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                .post();

    }

    public Response getOrderById(Integer orderId){
        return RestAssured
                .given()
                .pathParam("orderId", orderId)
                .header("accept", "application/xml")
                .get("/{orderId}");
    }
}
