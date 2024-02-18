package ge.halykbank.pum.web;

import ge.halykbank.pum.AbstractSpringMvcTestConfiguration;
import ge.halykbank.pum.entity.Role;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.entity.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
public class AdminControllerTest extends AbstractSpringMvcTestConfiguration {
    public AdminControllerTest() {
        super("testsuperuser", "password");
    }

    @Test
    void test_saveSingleUser() throws Exception {
        User user = new User("nikushakajaia", "sorokravaxar", Role.ADMIN);
        performPOSTAndExpectStatusCreated(user, "/admin/users/save");
    }

    @Test
    void test_saveInvalidUserValidation() throws Exception {
        User user = new User("A", "B", Role.ADMIN);
        performPOSTAndExpectStatusBadRequest(user, "/admin/users/save");
    }

    @Test
    void test_saveAllUsers() throws Exception {
        List<User> users = List.of(new User("testtest", "testtest123", Role.USER));
        performPOSTAndExpectStatusCreatedAndExpect(users, users, "/admin/users/save/all");
    }

    @Test
    @Sql(statements = "INSERT INTO users (id, username, password, role) VALUES(444,'nikusha','kajaia','USER')")
    void test_findById() throws Exception {
        var expected = UserDTO.builder()
                .id(444)
                .username("nikusha")
                .password("kajaia")
                .role(Role.USER)
                .build();
        performGETAndExpectStatusOkAndExpect(expected, "/admin/users/id/444");
    }

    @Test
    void test_findById_noResult() throws Exception {
        performGETAndExpectStatusIsNotFound("/admin/users/id/1000");
    }

    @Test
    @Sql(statements = "INSERT INTO users (id, username, password, role) VALUES(445,'nikushi','kajaia','USER')")
    void test_findByUsername() throws Exception {
        var expected = UserDTO.builder()
                .id(445)
                .username("nikushi")
                .password("kajaia")
                .role(Role.USER)
                .build();
        performGETAndExpectStatusOkAndExpect(expected, "/admin/users/username/nikushi");
    }

    @Test
    void test_findByUsername_noResult() throws Exception {
        performGETAndExpectStatusIsNotFound("/admin/users/username/test");
    }

    @Test
    void test_emptyDeleteById() throws Exception {
        performDELETEAndExpect5xx("/admin/users/delete/9999");
    }

    @Test
    @Sql(statements = "INSERT INTO users (id, username, password, role) VALUES(446,'nikusho','kajaia','USER')")
    void test_update() throws Exception {
        User user = new User(446, "nikushe", "kajaia", Role.USER);
        performUpdateAndExpectStatusOk(UserDTO.fromUser(user),"/admin/users/update");
    }
}