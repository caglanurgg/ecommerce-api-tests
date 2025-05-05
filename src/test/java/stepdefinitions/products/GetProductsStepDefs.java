package stepdefinitions.products;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import base.BaseRequest;
import org.junit.Assert;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import pojos.Product;
import pojos.Rating;

import java.util.List;

public class GetProductsStepDefs {

    private Response response;

    @Given("the user sends a GET request to {string}")
    public void the_user_sends_a_get_request_to(String endpoint) {
        response = BaseRequest.sendRequest(endpoint);
       // response.prettyPrint();
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
        assertTrue("Response body is not a JSON array!", responseBody.startsWith("[") && responseBody.endsWith("]"));
    }

    @Then("the response body should be empty")
    public void theResponseBodyShouldBeEmpty() {
        String responseBody = response.getBody().asString().trim();

        if (responseBody.isEmpty()) {
            System.out.println("Test passed: Response body is correctly empty.");
        } else {
            Assert.fail("Test failed: Response body is NOT empty!");
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
            case "description":
                return product.getDescription();
            case "image":
                return product.getImage();
            case "rating":
                return product.getRating();
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }
    }

    @Then("each product object should have an {string}, {string}, {string}, {string}, {string}, {string}, and {string} field")
    public void each_product_object_should_have_an_and_field(String field1, String field2, String field3, String field4, String field5, String field6, String field7) {
        List<Product> products = response.jsonPath().getList(".", Product.class);

        for (Product product : products) {
            assertNotNull(field1 + " is missing!", getFieldValue(field1, product));
            assertNotNull(field2 + " is missing!", getFieldValue(field2, product));
            assertNotNull(field3 + " is missing!", getFieldValue(field3, product));
            assertNotNull(field4 + " is missing!", getFieldValue(field4, product));
            assertNotNull(field5 + " is missing!", getFieldValue(field5, product));
            assertNotNull(field6 + " is missing!", getFieldValue(field6, product));
            assertNotNull(field7 + " is missing!", getFieldValue(field7, product));
        }
    }

    @And("the response body should not be empty")
    public void theResponseBodyShouldNotBeEmpty() {
        String responseBody = response.getBody().asString();
        Assert.assertFalse("Response body is empty!", responseBody.isEmpty());
    }

    @Then("the response time should be less than {int} seconds")
    public void the_response_time_should_be_less_than_seconds(Integer seconds) {
        response.then().time(lessThan((long) seconds * 1000));
    }

    @Then("each product should have a price greater than {double}")
    public void each_product_should_have_a_price_greater_than(Double threshold) {
        List<Product> products = response.jsonPath().getList(".", Product.class);
        for (Product product : products) {
            assertTrue("Price should be greater than " + threshold + " but found: " + product.getPrice(),
                    product.getPrice() > threshold);
        }
    }

    @Then("each product should contain rating with {string} and {string} fields")
    public void eachProductShouldContainRatingWithAndFields(String field1, String field2) {
        List<Product> products = response.jsonPath().getList(".", Product.class);

        // Her ürün için rating alanını kontrol ediyoruz
        for (Product product : products) {
            Rating rating = product.getRating();

            // rate ve count alanlarının null olmadığını ve geçerli değerler içerdiğini kontrol ediyoruz
            assertNotNull(field1 + " field is missing!", rating.getRate());
            assertNotNull(field2 + " field is missing!", rating.getCount());

            // Ayrıca, rate'in geçerli bir değer (örneğin, 0 ile 5 arasında bir değer) olup olmadığını kontrol edebiliriz
            assertTrue(field1 + " is not a valid rating!", rating.getRate() >= 0 && rating.getRate() <= 5);
            assertTrue(field2 + " is not a valid count!", rating.getCount() >= 0);
        }
    }
}
