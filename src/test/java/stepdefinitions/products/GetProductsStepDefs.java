package stepdefinitions.products;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utilities.ConfigurationReader;
import utilities.SpecBuilder;
import base.BaseRequest;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetProductsStepDefs {

    @Given("the API is available")
    public void the_api_is_available() {
        baseURI = ConfigurationReader.get("base_url");
        System.out.println(ConfigurationReader.get("base_url"));
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        // Response nesnesini burada BaseRequest'e atıyoruz
        BaseRequest.response = given().spec(SpecBuilder.getRequestSpec())
                .when().get(endpoint);
    }

    @Then("the response status code should be {string}")
    public void the_response_status_code_should_be(String expectedStatusCode) {
        // Response'u BaseRequest'ten alıyoruz
        assertEquals(expectedStatusCode, String.valueOf(BaseRequest.response.statusCode()));
    }

    @Then("the response should contain a list of products")
    public void the_response_should_contain_a_list_of_products() {
        // Response'u BaseRequest'ten alıyoruz
        assertTrue(BaseRequest.response.jsonPath().getList("$").size() > 0, "Product list is empty");
    }
}
