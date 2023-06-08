package com.uyghur.java.securityservice.controller;

import com.uyghur.java.securityservice.model.RequestDTO;
import com.uyghur.java.securityservice.model.User;
import com.uyghur.java.securityservice.model.UserDTO;
import com.uyghur.java.securityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    public final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/save-user")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> saveNewUser(@RequestBody @Valid User user) {
        log.debug("saveNewUser method starts here, in UserController");
        UserDTO userSaved = userService.saveUser(user);
        log.info("New User has been successfully saved in UserController");
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody RequestDTO request) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if(authentication.isAuthenticated()) {
            log.info("User with username:{%s} has been successfully authenticated in UserController".formatted(request.getUsername()));
            return new ResponseEntity<>(userService.generateJwtToken(request.getUsername()), HttpStatus.CREATED);
        } else {
            log.debug("Bad Credentials!");
            throw new BadCredentialsException("Bad Credentials!");
        }

    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        userService.validateJwtToken(token);
        log.info("Token is valid");
        return ResponseEntity.ok("Token:{%s} is valide".formatted(token));
    }

}
