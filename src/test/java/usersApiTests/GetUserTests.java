package usersApiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import usersApi.responseModels.ErrorResponseModel;
import usersApi.responseModels.UserResponseModel;
import usersApi.client.UsersApiHttpClient;
import utils.JsonHelper;
import static org.junit.jupiter.api.Assertions.*;

public class GetUserTests extends CreateUserBeforeTest {

    @Test
    @Tag("positive")
    public void GetUserByEmail_ExistingUser() throws JsonProcessingException {
        String email = testUser.email;
        Response response = UsersApiHttpClient.getUserByEmail(email);
        UserResponseModel responseUser = JsonHelper.deserializeResponse(response, UserResponseModel.class);
        JsonHelper.printJson(responseUser);
        assertEquals(200,  response.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(email, responseUser.email, "User email is not " + email);
    }

    @Test
    @Tag("negative")
    public void GetUserByEmail_NotExistingUser() throws JsonProcessingException {
        //generate new email and trying to get user with this email
        String email = faker.internet().safeEmailAddress();
        //get user with not existing email
        Response response = UsersApiHttpClient.getUserByEmail(email);
        ErrorResponseModel responseError = JsonHelper.deserializeResponse(response, ErrorResponseModel.class);
        JsonHelper.printJson(responseError);
        assertEquals(200,  response.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(responseError.type,"error", "Response is not error");
        assertTrue(responseError.message.contains(email), "Response message does not contain requested email");
    }

    @Test
    @Tag("negative")
    public void GetUserByEmail_InvalidEmailSyntax() throws JsonProcessingException {
        String email = testUser.email.split("@")[0]; //trim string after symbol @ -> invalid email syntax
        Response response = UsersApiHttpClient.getUserByEmail(email);
        ErrorResponseModel responseError = JsonHelper.deserializeResponse(response, ErrorResponseModel.class);
        JsonHelper.printJson(responseError);
        assertEquals(200,  response.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(responseError.type,"error", "Response is not error");
    }
}
