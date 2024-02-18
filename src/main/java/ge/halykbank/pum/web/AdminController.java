package ge.halykbank.pum.web;

import ge.halykbank.pum.entity.Role;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.entity.dto.UserDTO;
import ge.halykbank.pum.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AdminController.WEB_CONTEXT)
@Secured("ADMIN")
@SecurityRequirement(name = "api")
@Slf4j
public class AdminController {
    public static final String WEB_CONTEXT = "/admin";
    private final UserRepository repository;

    public AdminController(UserRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users got retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})})
    @GetMapping("/users/all")
    public List<UserDTO> findAll() {
        log.debug("Received request for users retrieval");
        return repository.findAll().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

    @Operation(summary = "Save an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Invalid body",
                    content = @Content)
    })
    @PostMapping("/users/save")
    public ResponseEntity<User> save(final @RequestBody @Valid User user) {
        log.debug("Received request to save user: {}", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(user));
    }

    @Operation(summary = "Save all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Users saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @PostMapping("/users/save/all")
    public ResponseEntity<List<User>> saveAll(final @RequestBody List<@Valid User> list) {
        log.debug("Received request to save multiple users");
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.saveAll(list));
    }

    @Operation(summary = "update an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "User not found",
                    content = @Content)
    })
    @PutMapping("/users/update")
    public void update(@Parameter(description = "User to update") final @RequestBody UserDTO userDTO) {
        log.debug("Received request to update user: {}", userDTO.getUsername());
        repository.save(userDTO.toUser());
    }

    @Operation(summary = "Find an user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @GetMapping("/users/id/{id}")
    public UserDTO findById(final @PathVariable Integer id) {
        log.debug("Received request to find user by ID: {}", id);
        return UserDTO.fromUser(repository.getReferenceById(id));
    }

    @Operation(summary = "Find an user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping("/users/username/{username}")
    public UserDTO findByUsername(final @PathVariable String username) {
        log.debug("Received request to find user by username: {}", username);
        try {
            return UserDTO.fromUser(repository.getReferenceByUsername(username));
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Find an users by role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content)})
    @GetMapping("/users/role/{role}")
    public List<UserDTO> findByRole(final @PathVariable Role role) {
        log.debug("Received request to find users by role: {}", role);
        try {
            return repository.findAllByRole(role).stream().map(UserDTO::fromUser).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Delete user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @DeleteMapping("/users/delete/{id}")
    public void delete(final @PathVariable Integer id) {
        log.debug("Received request to delete user by ID: {}", id);
        repository.deleteById(id);
    }
}