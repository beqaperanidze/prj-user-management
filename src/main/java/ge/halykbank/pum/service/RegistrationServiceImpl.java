package ge.halykbank.pum.service;

import ge.halykbank.pum.auth.RegisterRequest;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.repository.UserRepository;
import ge.halykbank.pum.service.api.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(RegisterRequest request) {
        log.debug("Received registration request for user: {}", request.getUsername());
        boolean userExists = repository.getByUsername(request.getUsername()).isPresent();
        if (userExists) {
            return false;
        }
        String encoded = passwordEncoder.encode(request.getPassword());
        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoded);
        repository.save(user);
        return true;
    }
}
