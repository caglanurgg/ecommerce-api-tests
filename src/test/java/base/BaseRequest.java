package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.SpecBuilder;

import static org.junit.Assert.assertNotNull;

public class BaseRequest {

    public static Response response;

    // Response'u döndüren metod
    public static Response sendRequest(String endpoint) {
        response = RestAssured.given()
                .spec(SpecBuilder.getRequestSpec())  // SpecBuilder'dan base URL ve diğer ayarları alıyoruz
                .when()
                .get(endpoint);

        return response;  // response'u döndürüyoruz
    }

    public static Response sendPostRequest(String endpoint, String requestBody) {
        response = RestAssured.given()
                .spec(SpecBuilder.getRequestSpec())
                .body(requestBody)
                .post(endpoint);

        if (response == null) {
            throw new RuntimeException("API response is NULL! Check API accessibility.");
        }

        System.out.println("Response received: " + response.prettyPrint());
        return response;
    }
}

