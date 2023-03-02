import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.requests.storeRequests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class storeRequestsTests extends storeRequests {
    private final int ORDER_ID = 1;
    private final int PET_ID = 1;
    private final int NON_EXISTING_ORDER_ID = 1010101;
    @BeforeClass
    public void initializer(){
        RestAssured.baseURI = "http://localhost:8080/api/v3/store/order";
    }
    @Test(description = "TC005 - Verify new orders can be added to the store successfully." +
            " The status code retrieved should be 200.")
    public void ordersCanBeAddedToStoresSuccessfully(){
        String  requestBody = "{\n" +
                "  \"id\": "+ORDER_ID+",\n" +
                "  \"petId\": "+PET_ID+",\n" +
                "  \"quantity\": 7,\n" +
                "  \"shipDate\": \"2023-03-02T12:07:57.584Z\",\n" +
                "  \"status\": \"approved\",\n" +
                "  \"complete\": true\n" +
                "}";

        Response response = postNewOrder(requestBody);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "TC006 - Verify new orders cannot be added to the store if the " +
            " request body does not have required values. The status code retrieved should be 400.")
    public void ordersCanNotBeAddedToStoresWithoutProperRequiredBody(){
        String  requestBody = "{\n" +
                "  \"id\": ,\n" +
                "  \"petId\": ,\n" +
                "  \"quantity\": 7,\n" +
                "  \"shipDate\": \"2023-03-02T12:07:57.584Z\",\n" +
                "  \"status\": \"approved\",\n" +
                "  \"complete\": true\n" +
                "}";

        Response response = postNewOrder(requestBody);
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.getBody().jsonPath().get("message"),
                "Input error: unable to convert input to io.swagger.petstore.model.Order");
    }

    @Test(description = "TC007 - Verify orders can be retrieved by their IDs successfully." +
            " The status code retrieved should be 200 and the order Id the one sent as parameter.")
    public void ordersCanBeGottenByTheCorrectId(){
        Response response = getOrderById(ORDER_ID);
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getBody().xmlPath().getInt("order.id"), ORDER_ID);
        assertEquals(response.getBody().xmlPath().getInt("order.petId"), PET_ID);
    }

    @Test(description = "TC008 - Verify orders can not be retrieved if the IDs do not exist." +
            " The status code retrieved should be 404.")
    public void ordersCanNotBeGottenByNonExistingIds(){
        Response response = getOrderById(NON_EXISTING_ORDER_ID);
        assertEquals(response.getStatusCode(), 404);
    }
}
