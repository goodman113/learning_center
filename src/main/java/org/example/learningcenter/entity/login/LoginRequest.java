package org.example.learningcenter.entity.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private boolean rememberMe;
}
