package ge.halykbank.pum.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "api")
@RequestMapping(UserController.WEB_CONTEXT)
@Slf4j
public class UserController {
    public static final String WEB_CONTEXT = "/users";

    @GetMapping("/me")
    public String me() {
        log.debug("Received request to get current user information");
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
