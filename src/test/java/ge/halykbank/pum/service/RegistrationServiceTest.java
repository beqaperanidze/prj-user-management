package ge.halykbank.pum.service;

import ge.halykbank.pum.auth.RegisterRequest;
import ge.halykbank.pum.entity.Role;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.repository.UserRepository;
import ge.halykbank.pum.service.api.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class RegistrationServiceTest {
    @Autowired
    RegistrationService service;
    @Autowired
    UserRepository userRepository;

    @Test
    void test_register() {
        var registered = service.register(new RegisterRequest("nikushu", "kajaia"));
        Assertions.assertTrue(registered);
        User user = userRepository.getReferenceByUsername("nikushu");
        Assertions.assertEquals(user.getUsername(), "nikushu");
        Assertions.assertEquals(user.getPassword(), "kajaia");
        Assertions.assertEquals(user.getRole(), Role.USER);
    }

    @Test
    @Sql(statements = "INSERT INTO users (username, password, role) VALUES ('evgo', 'soroka', 'USER')")
    void test_userAlreadyExists() {
        var registered = service.register(new RegisterRequest("evgo", "soroka"));
        Assertions.assertFalse(registered);
    }
}
