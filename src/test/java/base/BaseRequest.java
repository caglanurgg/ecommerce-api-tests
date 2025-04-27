package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseRequest {

    public static Response response;

    public static Response sendRequest(String endpoint) {
        // Burada baseURL ve gerekli yapılamalar yapılabilir
        response = RestAssured.given()
                .baseUri("https://fakestoreapi.com")
                .when()
                .get(endpoint);  // GET isteği örneği
        return response;
    }
}
