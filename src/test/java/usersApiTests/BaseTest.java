package usersApiTests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import usersApi.requestModels.RegisterUserRequestModel;
import utils.UserApiModelBuilder;

public class BaseTest {
    protected static Faker faker;
    protected static RegisterUserRequestModel testUser;

    @BeforeEach
    public void BaseTestSetUp(){
        faker = new Faker();
        testUser = UserApiModelBuilder.buildRegisterUserRequestModel();
    }
}
