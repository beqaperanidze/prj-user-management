package ge.halykbank.pum.service;

import ge.halykbank.pum.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
class UserDetailsServiceImpl implements UserDetailsService {
    private final static String USER_NOT_FOUND = "user not found";

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return repository.getReferenceByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
    }
}