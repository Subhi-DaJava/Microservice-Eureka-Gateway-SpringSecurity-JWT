package com.uyghur.java.securityservice.controller;

import com.uyghur.java.securityservice.model.RequestDTO;
import com.uyghur.java.securityservice.model.User;
import com.uyghur.java.securityservice.model.UserDTO;
import com.uyghur.java.securityservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<UserDTO> saveNewUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody RequestDTO request) {
        return new ResponseEntity<>(userService.generateJwtToken(request.getUsername()), HttpStatus.CREATED);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        userService.validateJwtToken(token);
        log.info("Token is valid");
        return ResponseEntity.ok("Token:{%s} is valide".formatted(token));
    }

}
