package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.SpecBuilder;

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
}

