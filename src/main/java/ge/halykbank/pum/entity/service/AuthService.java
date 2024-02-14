package ge.halykbank.pum.entity.service;

import ge.halykbank.pum.entity.User;

import javax.naming.AuthenticationException;

public interface AuthService {
    User login(String username, String password) throws AuthenticationException;

}
