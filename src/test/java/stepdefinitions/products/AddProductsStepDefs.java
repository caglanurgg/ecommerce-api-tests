package stepdefinitions.products;

import base.BaseRequest;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import utilities.ApiUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
}
