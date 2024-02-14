package ge.halykbank.pum.repository;

import ge.halykbank.pum.entity.Role;
import ge.halykbank.pum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getReferenceByUsername(String username);
    List<User> findAllByRole(Role role);
}
