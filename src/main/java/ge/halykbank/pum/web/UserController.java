package ge.halykbank.pum.web;

import ge.halykbank.pum.entity.Role;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.entity.dto.UserDTO;
import ge.halykbank.pum.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UserController.WEB_CONTEXT)
public class UserController {
    public static final String WEB_CONTEXT = "/users";

    private final UserRepository repository;


    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    User test = new User();

    @GetMapping("/me")
    public UserDTO me() {
        return null;
    }


    @PostMapping("/save")
    public ResponseEntity<User> save(final @RequestBody @Valid User user) {
        return ResponseEntity.of(Optional.of(repository.save(user)));
    }

    @PostMapping("/save/all")
    public ResponseEntity<List<User>> saveAll(final @RequestBody List<@Valid User> list) {
        return ResponseEntity.of(Optional.of(repository.saveAll(list)));
    }

    @GetMapping("/id/{id}")
    public UserDTO findById(final @PathVariable Integer id) {
        return UserDTO.fromUser(repository.getReferenceById(id));
    }

    @GetMapping("/username/{username}")
    public UserDTO findByUsername(final @PathVariable String username) {
        try {
            return UserDTO.fromUser(repository.getReferenceByUsername(username));
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/role/{role}")
    public List<UserDTO> findByRole(final @PathVariable Role role) {
        try {
            return repository.findAllByRole(role).stream().map(UserDTO::fromUser).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }
}
