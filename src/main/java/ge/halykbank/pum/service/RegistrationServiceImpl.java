package ge.halykbank.pum.service;

import ge.halykbank.pum.auth.RegisterRequest;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.repository.UserRepository;
import ge.halykbank.pum.service.api.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository repository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean register(RegisterRequest request) {
        boolean userExists = repository.getByUsername(request.getUsername()).isPresent();
        if (userExists) {
            return false;
        }
        String encoded = bCryptPasswordEncoder.encode(request.getPassword());
        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoded);
        repository.save(user);
        return true;
    }
}
