package usersApiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import usersApi.client.UsersApiHttpClient;
import usersApi.requestModels.RegisterUserRequestModel;
import usersApi.responseModels.ErrorResponseModel;
import usersApi.responseModels.UserResponseModel;
import utils.JsonHelper;
import utils.SetFieldNewValueHelper;
import utils.UserApiModelBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterUserTests extends BaseTest{

    @Test
    @Tag("positive")
    public void RegisterUser_ValidData() throws JsonProcessingException {
        Response response = UsersApiHttpClient.registerUser(testUser);
        UserResponseModel responseUser = JsonHelper.deserializeResponse(response, UserResponseModel.class);
        JsonHelper.printJson(responseUser);
        assertEquals(200,  response.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(testUser.email, responseUser.email, "User email is not " + testUser.email);
        assertEquals(testUser.name, responseUser.name, "User name is not " + testUser.name);
//        assertEquals(testUser.password, responseUser.password, "User password is not " + testUser.password);
    }

    @ParameterizedTest
    @Tag("negative")
    @MethodSource ("GetUserModelWithMissingRequiredParameter")
    public void RegisterUser_WithoutOneField(RegisterUserRequestModel testUser) throws JsonProcessingException {
        Response response = UsersApiHttpClient.registerUser(testUser);
        ErrorResponseModel responseError = JsonHelper.deserializeResponse(response, ErrorResponseModel.class);
        JsonHelper.printJson(responseError);
        assertEquals(200,  response.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(responseError.type,"error", "Response is not error");
    }

    @Test
    @Tag("negative")
    public void RegisterUser_WithInvalidEmailSyntax() throws JsonProcessingException {
        testUser.email = testUser.email.split("@")[0];
        Response response = UsersApiHttpClient.registerUser(testUser);
        ErrorResponseModel responseError = JsonHelper.deserializeResponse(response, ErrorResponseModel.class);
        JsonHelper.printJson(responseError);
        assertEquals(200,  response.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(responseError.type,"error", "Response is not error");
    }

    private static Stream<Arguments> GetUserModelWithMissingRequiredParameter() throws NoSuchFieldException, IllegalAccessException {

        String[] fields = new String[]{"name", "email", "password"};
        ArrayList<RegisterUserRequestModel> users = new ArrayList<>();
        RegisterUserRequestModel newUser;
        for (String fieldName:fields) {
            newUser = UserApiModelBuilder.buildRegisterUserRequestModel();
            users.add(newUser);
            SetFieldNewValueHelper.setFieldValue(newUser, fieldName, null);
        }
        //TODO:
        return Stream.of(
                Arguments.of(users.get(0)),
                Arguments.of(users.get(1)),
                Arguments.of(users.get(2))
        );
    }
}
