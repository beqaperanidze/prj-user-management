package ge.halykbank.pum.auth;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.WEB_CONTEXT)
@AllArgsConstructor
public class AuthController {
    public static final String WEB_CONTEXT = "/registration";
    private AuthService authService;

    @PostMapping
    public String register(@RequestBody AuthRequest request) {
        return authService.register(request);
    }


}
