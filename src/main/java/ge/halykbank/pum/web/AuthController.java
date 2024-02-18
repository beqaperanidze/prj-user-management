package ge.halykbank.pum.web;

import ge.halykbank.pum.auth.RegisterRequest;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.service.api.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequest request) {
        log.debug("Received registration request for user: {}", request.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.register(request));
    }
}