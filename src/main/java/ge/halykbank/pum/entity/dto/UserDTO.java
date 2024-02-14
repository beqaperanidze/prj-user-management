package ge.halykbank.pum.entity.dto;

import ge.halykbank.pum.entity.Role;
import ge.halykbank.pum.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private Role role;

    public static UserDTO fromUser(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
