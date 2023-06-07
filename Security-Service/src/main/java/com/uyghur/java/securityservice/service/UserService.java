package com.uyghur.java.securityservice.service;

import com.uyghur.java.securityservice.model.User;
import com.uyghur.java.securityservice.model.UserDTO;

public interface UserService {
    UserDTO saveUser(User user);
    String generateJwtToken(String username);
    void validateJwtToken(String jwt_token);
}
