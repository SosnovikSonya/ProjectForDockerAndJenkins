package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonHelper {

    public static <T> T deserializeResponse(Response response, Class<T> valueType) throws JsonProcessingException {
        return new ObjectMapper().readValue(response.getBody().asString(), valueType);
    }

    public static void printJson(Object jsonObject) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject));
    }
}