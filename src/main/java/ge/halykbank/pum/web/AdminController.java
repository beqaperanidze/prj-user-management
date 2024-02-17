package ge.halykbank.pum.web;

import ge.halykbank.pum.entity.Role;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.entity.dto.UserDTO;
import ge.halykbank.pum.repository.UserRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AdminController.WEB_CONTEXT)
@Secured("ADMIN")
@SecurityRequirement(name = "api")
public class AdminController {
    public static final String WEB_CONTEXT = "/admin";
    private final UserRepository repository;

    public AdminController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users/all")
    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> save(final @RequestBody @Valid User user) {
        return ResponseEntity.of(Optional.of(repository.save(user)));
    }

    @PostMapping("/users/save/all")
    public ResponseEntity<List<User>> saveAll(final @RequestBody List<@Valid User> list) {
        return ResponseEntity.of(Optional.of(repository.saveAll(list)));
    }

    @PutMapping("/users/update")
    public void update(@Parameter(description = "User to update") final @RequestBody UserDTO userDTO) {
        repository.save(userDTO.toUser());
    }

    @GetMapping("/users/id/{id}")
    public UserDTO findById(final @PathVariable Integer id) {
        return UserDTO.fromUser(repository.getReferenceById(id));
    }

    @GetMapping("/users/username/{username}")
    public UserDTO findByUsername(final @PathVariable String username) {
        try {
            return UserDTO.fromUser(repository.getReferenceByUsername(username));
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/users/role/{role}")
    public List<UserDTO> findByRole(final @PathVariable Role role) {
        try {
            return repository.findAllByRole(role).stream().map(UserDTO::fromUser).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/users/delete/{id}")
    public void delete(final @PathVariable Integer id) {
        repository.deleteById(id);
    }
}
