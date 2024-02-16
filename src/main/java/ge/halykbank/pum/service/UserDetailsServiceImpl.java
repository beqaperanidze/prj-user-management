package ge.halykbank.pum.service;

import ge.halykbank.pum.entity.User;
import ge.halykbank.pum.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static String USER_NOT_FOUND =
            "User not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.getReferenceByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
    }

    public String singUpUser(User user) {
        boolean userExists = userRepository
                .getByUsername(user.getUsername()).
                isPresent();

        String encoded = bCryptPasswordEncoder
                .encode(user.getPassword());
        user.setPassword(encoded);
        userRepository.save(user);

        return "it works";
    }
}