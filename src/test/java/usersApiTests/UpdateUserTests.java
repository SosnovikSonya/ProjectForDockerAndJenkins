package usersApiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import usersApi.client.UsersApiHttpClient;
import usersApi.requestModels.UpdateFieldRequestModel;
import usersApi.responseModels.ErrorResponseModel;
import usersApi.responseModels.UserResponseModel;
import utils.JsonHelper;
import utils.UserApiModelBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateUserTests extends CreateUserBeforeTest {

    private static String newName;

    @BeforeEach
    public void UpdateUserTestsSetUp(){
        newName = faker.name().fullName();
    }

    @Test
    @Tag("positive")
    public void updateUserField_ExistingUser_NameField() throws JsonProcessingException {
        UpdateFieldRequestModel requestModel = UserApiModelBuilder.buildUpdateFieldRequestModel
                                               (testUser.email, "name", newName);
        Response updateUserFieldResponse = UsersApiHttpClient.updateUserField(requestModel);
        assertEquals(200,  updateUserFieldResponse.getStatusCode(), "Status Code is not 200 OK");
        //check that user name is new
        Response getUserResponse = UsersApiHttpClient.getUserByEmail(testUser.email);
        UserResponseModel responseUser = JsonHelper.deserializeResponse(getUserResponse, UserResponseModel.class);
        JsonHelper.printJson(responseUser);
        assertEquals(200,  getUserResponse.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(newName, responseUser.name, "User name is not new, it is - " + responseUser.name);
    }

    @Test
    @Tag("negative")
    public void updateUserField_NotExistingUser() throws JsonProcessingException {
        //generate new email and trying to update user with this email
        String email = faker.internet().safeEmailAddress();
        //String newName = faker.name().fullName();
        UpdateFieldRequestModel requestModel = UserApiModelBuilder.buildUpdateFieldRequestModel
                (email, "name", newName);
        Response updateUserFieldResponse = UsersApiHttpClient.updateUserField(requestModel);
        ErrorResponseModel responseError = JsonHelper.deserializeResponse(updateUserFieldResponse, ErrorResponseModel.class);
        JsonHelper.printJson(responseError);
        assertEquals(200,  updateUserFieldResponse.getStatusCode(), "Status Code is not 200 OK");
        assertEquals(responseError.type,"error", "Response is not error");
    }

}
