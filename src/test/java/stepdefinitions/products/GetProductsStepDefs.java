package stepdefinitions.products;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import utilities.ConfigurationReader;
import utilities.SpecBuilder;
import base.BaseRequest;
import org.junit.Assert;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import pojos.Product;
import java.util.List;

public class GetProductsStepDefs {

    private Response response;

    @Given("the user sends a GET request to {string}")
    public void the_user_sends_a_get_request_to(String endpoint) {
        response = BaseRequest.sendRequest(endpoint);
        response.prettyPrint();
    }

    @Then("the HTTP status code should be {int}")
    public void the_http_status_code_should_be(Integer expectedStatusCode) {
        response.then()
                .assertThat()
                .statusCode(expectedStatusCode);
    }

    @Then("the response body should be in JSON array format")
    public void the_response_body_should_be_in_json_array_format() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue("Response body is not a JSON array!", responseBody.startsWith("[") && responseBody.endsWith("]"));
    }

    @Then("each product object should have an {string}, {string}, {string}, and {string} field")
    public void each_product_object_should_have_an_and_field(String field1, String field2, String field3, String field4) {
        List<Product> products = response.jsonPath().getList("products", Product.class);

        for (Product product : products) {
            Assert.assertNotNull(field1 + " is missing!", getFieldValue(field1, product));
            Assert.assertNotNull(field2 + " is missing!", getFieldValue(field2, product));
            Assert.assertNotNull(field3 + " is missing!", getFieldValue(field3, product));
            Assert.assertNotNull(field4 + " is missing!", getFieldValue(field4, product));
        }
    }

    private Object getFieldValue(String field, Product product) {
        switch (field) {
            case "id":
                return product.getId();
            case "title":
                return product.getTitle();
            case "price":
                return product.getPrice();
            case "category":
                return product.getCategory();
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }
    }
}
