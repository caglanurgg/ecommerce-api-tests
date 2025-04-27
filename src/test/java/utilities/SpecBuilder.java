package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigurationReader.get("base_url"))
                .setRelaxedHTTPSValidation()
                .build();
    }
}
