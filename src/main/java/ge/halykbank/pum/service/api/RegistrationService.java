package ge.halykbank.pum.service.api;

import ge.halykbank.pum.auth.RegisterRequest;

/**
 * Service for User registration.
 */
public interface RegistrationService {
    /**
     * This method is used for User registration.
     * @param request Registration Request DTO.
     * @return True in case of successful registration.
     * False in case User already exist.
     * @see RegisterRequest
     */
    boolean register(RegisterRequest request);
}
