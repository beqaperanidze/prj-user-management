package ge.halykbank.pum.auth;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String register(AuthRequest authRequest);
}
