package utils;

import com.github.javafaker.Faker;
import usersApi.requestModels.RegisterUserRequestModel;
import usersApi.requestModels.UpdateFieldRequestModel;

public class UserApiModelBuilder {

    public static RegisterUserRequestModel buildRegisterUserRequestModel(){
        Faker faker = new Faker();
        RegisterUserRequestModel requestModel = new RegisterUserRequestModel();
        requestModel.name = faker.name().fullName();
        requestModel.email = faker.internet().safeEmailAddress();
        requestModel.password = faker.internet().password();
        return requestModel;
    }

    public static UpdateFieldRequestModel buildUpdateFieldRequestModel(String email, String fieldName, String fieldValue){
        UpdateFieldRequestModel requestModel = new UpdateFieldRequestModel();
        requestModel.email = email;
        requestModel.field = fieldName;
        requestModel.value = fieldValue;
        return requestModel;
    }
}
