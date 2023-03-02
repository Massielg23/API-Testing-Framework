import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.requests.petRequests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class petRequestsTests extends petRequests {
    private final int PET_ID = 1;
    private final int NON_EXISTING_PET_ID = 1010101;
    @BeforeClass
    public void initializer(){
        RestAssured.baseURI = "http://localhost:8080/api/v3/pet";
    }
    @Test(description = "TC001 - Verify new pets can be added to the store successfully." +
            " The status code retrieved should be 200.")
    public void petsCanBeAddedToStoresSuccessfully(){
        String  requestBody = "{\n" +
                "  \"id\": "+PET_ID+",\n" +
                "  \"name\": \"bulldog\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dogs\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"tag1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Response response = postAddNewPetToStore(requestBody);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "TC002 - Verify new pets cannot be added to the store if the request " +
            "body does not have required values. The status code retrieved should be 400.")
    public void petsCanNotBeAddedToStoresWithoutProperRequiredBody(){
        String  requestBody = "{\n" +
                "  \"id\": ,\n" +
                "  \"name\": \"\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dogs\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"tag1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Response response = postAddNewPetToStore(requestBody);
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.getBody().xmlPath().get("ApiError.message").toString(),
                "Input error: unable to convert input to io.swagger.petstore.model.Pet");
    }

    @Test(description = "TC003 - Verify pets can be retrieved by their IDs successfully." +
            " The status code retrieved should be 200 and the pet Id the one sent as parameter.")
    public void petsCanBeGottenByTheCorrectId(){
        Response response = getPetById(PET_ID);
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getBody().xmlPath().getInt("pet.id"), PET_ID);
    }

    @Test(description = "TC004 - Verify pets can not be retrieved if the IDs do not exist." +
            " The status code retrieved should be 404.")
    public void petsCanNotBeGottenByNonExistingIds(){
        Response response = getPetById(NON_EXISTING_PET_ID);
        assertEquals(response.getStatusCode(), 404);
    }
}
