package ge.halykbank.pum.web;

import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.entity.dto.LoginRequest;
import ge.halykbank.pum.entity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping(AuthController.WEB_CONTEXT)
public class AuthController {
    public static final String WEB_CONTEXT = "/login";
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User authenticatedUser = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(authenticatedUser);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
