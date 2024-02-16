package ge.halykbank.pum.auth;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * This class represents DTO for registration request.
 *
 * @author Beqa Peranidze
 */
@Getter
@AllArgsConstructor
@ToString
public class RegisterRequest {
    private final String username;
    private final String password;
}
