package org.example.learningcenter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningcenter.config.JwtUtils;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.login.LoginRequest;
import org.example.learningcenter.entity.login.LoginResponse;
import org.example.learningcenter.entity.login.TokenDto;
import org.example.learningcenter.entity.model.User;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.UserRepository;
import org.example.learningcenter.service.AuthService;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.getLoginResponseResponseEntity(request));
    }


}
