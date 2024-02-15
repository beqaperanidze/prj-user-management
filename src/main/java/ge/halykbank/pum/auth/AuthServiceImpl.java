package ge.halykbank.pum.auth;

import ge.halykbank.pum.entity.Role;
import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public String register(AuthRequest request) {
        return userDetailsService.singUpUser(
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        Role.USER
                )
        );
    }
}
