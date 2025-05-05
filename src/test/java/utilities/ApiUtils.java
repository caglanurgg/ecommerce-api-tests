package utilities;

import com.github.javafaker.Faker;
import org.json.JSONObject;

public class ApiUtils {
    private static final Faker faker = new Faker();

    public static JSONObject createValidProduct() {
        JSONObject product = new JSONObject();
        product.put("id", faker.number().randomDigit());
        product.put("title", faker.commerce().productName());
        product.put("price", faker.number().randomDouble(2, 1, 500));
        product.put("description", faker.lorem().sentence());
        product.put("category", faker.commerce().department());
        product.put("image", faker.internet().url());

        return product;
    }

}
