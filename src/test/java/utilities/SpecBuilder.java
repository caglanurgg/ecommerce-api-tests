package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utilities.ConfigurationReader.get;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(get("base_url"))
                .setContentType(ContentType.JSON)
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }
}