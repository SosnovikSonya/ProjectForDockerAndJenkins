package usersApi.client;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import usersApi.requestModels.RegisterUserRequestModel;
import utils.Urls;

public class UsersApiHttpClient {

    public static Response getUserByEmail(String email)
    {
        RestAssured.baseURI = Urls.baseUrl;
        return RestAssured.given().queryParam("email", email).accept(ContentType.JSON).when().get(Urls.getUserUrl);
    }

    public static Response registerUser(RegisterUserRequestModel newUser)
    {
        RestAssured.baseURI = Urls.baseUrl;
        return RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(newUser)
                           .when().post(Urls.createUserUrl);
    }

    public static Response deleteUser(String email)
    {
        RestAssured.baseURI = Urls.baseUrl;
        return RestAssured.given().queryParam("email", email).accept(ContentType.JSON).when().delete(Urls.deleteUserUrl);
    }

    public static Response updateUserField(Object request)
    {
        RestAssured.baseURI = Urls.baseUrl;
        return RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request)
                           .when().put(Urls.updateUserFieldUrl);
    }
}
