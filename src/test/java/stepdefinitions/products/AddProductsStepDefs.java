package stepdefinitions.products;

import base.BaseRequest;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import utilities.ApiUtils;
import utilities.ScenarioContext;

import static org.junit.Assert.*;

public class AddProductsStepDefs {

    private Response response;
    private JSONObject requestBody;

    @Given("the user sends a POST request to {string} with valid product details")
    public void theUserSendsPostRequest(String endpoint) {
        requestBody = ApiUtils.createValidProduct();
        response = BaseRequest.sendPostRequest(endpoint, requestBody.toString());

        System.out.println("Response Body: " + response);

        assertNotNull("Response is NULL! Check API request.", response);

        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    @Then("the API should return a successful HTTP status code")
    public void theApiShouldReturnSuccessfulHttpStatusCode() {
        int actualStatusCode = response.getStatusCode();
        System.out.println("Actual Status Code: " + actualStatusCode);

        assertTrue("Unexpected status code: " + actualStatusCode, actualStatusCode == 200 || actualStatusCode == 201);
    }

    @Then("the response body should contain the product details")
    public void theResponseBodyShouldContainProductDetails() {
        String responseBody = response.getBody().asString();

        assertTrue(responseBody.contains("\"title\":"));
        assertTrue(responseBody.contains("\"price\":"));
        assertTrue(responseBody.contains("\"description\":"));
        assertTrue(responseBody.contains("\"category\":"));
        assertTrue(responseBody.contains("\"image\":"));
        assertTrue(responseBody.contains("\"id\":"));
    }

    @Then("the API should respond within {int} milliseconds")
    public void the_api_should_respond_within_milliseconds(int expectedTime) {
        long responseTime = response.getTime();
        System.out.println("Response Time: " + responseTime + "ms");

        assertTrue("Response took too long!", responseTime <= expectedTime);
    }

    @And("the user sends the same POST request again")
    public void theUserSendsSamePostRequestAgain() {
        Response secondResponse = BaseRequest.sendPostRequest("/products", requestBody.toString());

        // İlk yanıtla ikinciyi kıyaslayabilmek için sakla
        ScenarioContext.set("secondResponse", secondResponse);
    }

    @Then("the API should return the exact same response for both requests")
    public void the_api_should_return_the_same_response() {
        Response secondResponse = (Response) ScenarioContext.get("secondResponse");

        assertEquals("API returns inconsistent response!",
                response.getBody().asString(),
                secondResponse.getBody().asString());
    }

    @And("log a warning if the ID remains unchanged")
    public void log_a_warning_if_the_id_remains_unchanged() {
        Response secondResponse = (Response) ScenarioContext.get("secondResponse");

        int firstId = response.getBody().jsonPath().getInt("id");
        int secondId = secondResponse.getBody().jsonPath().getInt("id");

        if (firstId == secondId) {
            System.err.println("Warning: API is returning the same ID for duplicate POST requests!");
        }
    }
}
