package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetProductsStepDefs {

    Response response;

    @Given("the API is available")
    public void the_api_is_available() {
        baseURI = "https://fakestoreapi.com";
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given()
                .when()
                .get(endpoint);
    }

    @Then("the response status code should be {string}")
    public void the_response_status_code_should_be(String expectedStatusCode) {
        assertEquals(expectedStatusCode, String.valueOf(response.statusCode()));
    }

    @Then("the response should contain a list of products")
    public void the_response_should_contain_a_list_of_products() {
        // Verinin bir dizi (array) olduğunu kontrol eder
        assertTrue(response.jsonPath().getList("$").size() > 0, "Product list is empty");
    }
}
