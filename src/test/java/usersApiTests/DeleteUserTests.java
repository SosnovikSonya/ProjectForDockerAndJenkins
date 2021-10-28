package usersApiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import usersApi.client.UsersApiHttpClient;
import usersApi.responseModels.ErrorResponseModel;
import utils.JsonHelper;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteUserTests extends CreateUserBeforeTest {

    @Test
    @Tag("positive")
    public void DeleteUser_ExistingUser() throws JsonProcessingException {
        //delete user
        Response deleteUserResponse = UsersApiHttpClient.deleteUser(testUser.email);
        assertEquals(200,  deleteUserResponse.getStatusCode(), "Status Code is not 200 OK");
        //check that user doesn't exist via getUserByEmail method
        Response getUserResponse = UsersApiHttpClient.getUserByEmail(testUser.email);
        ErrorResponseModel responseError = JsonHelper.deserializeResponse(getUserResponse, ErrorResponseModel.class);
        JsonHelper.printJson(responseError);
        assertEquals(responseError.type,"error", "Response is not error");
        assertTrue(responseError.message.contains(testUser.email), "Response message does not contain requested email");
    }

    @Test
    @Tag("negative")
    public void DeleteUser_NotExistingUser() throws JsonProcessingException {
        //generate new email and trying to delete user with this email
        String email = faker.internet().safeEmailAddress();
        Response response = UsersApiHttpClient.deleteUser(email);
        ErrorResponseModel responseError = JsonHelper.deserializeResponse(response, ErrorResponseModel.class);
        JsonHelper.printJson(responseError);
        assertEquals(200,  response.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(responseError.type,"error", "Response is not error");
    }

    @ParameterizedTest
    @Tag("negative")
    @MethodSource("GetInvalidEmailParameter")
    public void DeleteUser_WithInvalidEmailSyntax(String email) throws JsonProcessingException {
        //String email = testUser.email.split("@")[0];
        Response response = UsersApiHttpClient.deleteUser(email);
        ErrorResponseModel responseError = JsonHelper.deserializeResponse(response, ErrorResponseModel.class);
        JsonHelper.printJson(responseError);
        assertEquals(200,  response.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(responseError.type,"error", "Response is not error");
    }

    private static Stream<Arguments> GetInvalidEmailParameter(){
        Faker faker = new Faker();
        return Stream.of(
                Arguments.of(faker.internet().emailAddress().split("@")[0]),
                Arguments.of("")
        );
    }

}
