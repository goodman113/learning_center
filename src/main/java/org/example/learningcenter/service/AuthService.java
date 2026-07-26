package org.example.learningcenter.service;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.config.JwtUtils;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.login.LoginRequest;
import org.example.learningcenter.entity.login.LoginResponse;
import org.example.learningcenter.entity.login.TokenDto;
import org.example.learningcenter.entity.model.User;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final long REMEMBER_ME_REFRESH_EXPIRY_SECONDS = 30L * 24 * 60 * 60;


    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;



    public LoginResponse getLoginResponseResponseEntity(LoginRequest request) {
        String phone = request.getPhone() != null ? request.getPhone() : request.getEmail();

        User user = userRepository.findByPhoneAndDeletedFalse(phone)
                .orElseThrow(() -> RestException.restThrow(ErrorType.INVALID_PHONE_NUMBER_OR_PASSWORD));

        if (!Objects.equals(request.getPassword(), user.getPassword())) {
            throw RestException.restThrow(ErrorType.INVALID_PHONE_NUMBER_OR_PASSWORD);
        }

        Map<String, Object> claims = jwtUtils.prepareClaims(user);
        TokenDto accessToken = jwtUtils.generateToken(user.getPhone(), claims, "access");
        TokenDto refreshToken = request.isRememberMe()
                ? jwtUtils.generateToken(user.getPhone(), claims, REMEMBER_ME_REFRESH_EXPIRY_SECONDS)
                : jwtUtils.generateToken(user.getPhone(), claims, "refresh");

        return LoginResponse.builder()
                .token(accessToken.getToken())
                .expiry(accessToken.getExpiry())
                .refreshToken(refreshToken.getToken())
                .refreshExpiry(refreshToken.getExpiry())
                .build();
    }
}
