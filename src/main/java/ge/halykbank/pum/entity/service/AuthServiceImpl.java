package ge.halykbank.pum.entity.service;

import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(String username, String password) throws AuthenticationException {
        User user = userRepository.getReferenceByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid username or password");
        }

        return user;

    }
}
