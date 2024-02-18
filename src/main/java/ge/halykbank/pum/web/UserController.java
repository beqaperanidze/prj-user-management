package ge.halykbank.pum.web;

import ge.halykbank.pum.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Get user's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})})
    @GetMapping("/me")
    public String me() {
        log.debug("Received request to get current user information");
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
