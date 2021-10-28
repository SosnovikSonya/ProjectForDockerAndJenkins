package usersApiTests;

import org.junit.jupiter.api.BeforeEach;
import usersApi.client.UsersApiHttpClient;

public class CreateUserBeforeTest extends BaseTest {

    @BeforeEach
    public void CreateUserBeforeTestSetUp(){
        UsersApiHttpClient.registerUser(testUser);
    }
}
