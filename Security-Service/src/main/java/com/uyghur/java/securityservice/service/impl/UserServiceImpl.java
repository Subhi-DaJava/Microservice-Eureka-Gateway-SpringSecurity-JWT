package com.uyghur.java.securityservice.service.impl;

import com.uyghur.java.securityservice.jwt_util.JwtService;
import com.uyghur.java.securityservice.model.User;
import com.uyghur.java.securityservice.model.UserDTO;
import com.uyghur.java.securityservice.repository.UserRepository;
import com.uyghur.java.securityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDTO saveUser(User user) {
        log.debug("saveUser method starts here in UserServiceImpl");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSaved = repository.save(user);

        UserDTO userReturned = new UserDTO(userSaved.getUsername());

        log.info("New user has been successfully saved with username:{%s}".formatted(userReturned.getUsername()));
        return userReturned;
    }

    @Override
    public String generateJwtToken(String username) {
        return jwtService.generateJwtToken(username);
    }

    @Override
    public void validateJwtToken(String jwt_token) {
        jwtService.validateToken(jwt_token);
    }
}
