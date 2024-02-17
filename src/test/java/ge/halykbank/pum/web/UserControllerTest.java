package ge.halykbank.pum.web;

import ge.halykbank.pum.AbstractSpringMvcTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTest extends AbstractSpringMvcTestConfiguration {
    public UserControllerTest() {
        super("testuser", "password");
    }

    @Test
    void test_getMe() throws Exception {
        performGETAndExpectStatusOkAndExpect("User{id=4, username='testuser', role=USER}", "/users/me");
    }

}
