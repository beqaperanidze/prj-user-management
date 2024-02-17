package ge.halykbank.pum.web;

import ge.halykbank.pum.auth.RegisterRequest;
import ge.halykbank.pum.service.api.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterRequest request) {
        log.debug("Received registration request for user: {}", request.getUsername());
        return registrationService.register(request);
    }
}