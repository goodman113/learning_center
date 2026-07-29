package org.example.learningcenter.validator;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.config.CustomUserDetails;
import org.example.learningcenter.entity.dto.user.UserCreateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.User;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository repository;


    public User validateIdAndGet(String id) {
        return repository.findById(id)
                .orElseThrow(() -> RestException.restThrow(ErrorType.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public void validate(UserCreateDto createDto) {

    }

    public String authenticateAndGetId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            assert principal != null;
            return principal.getUserId();
        }
        throw RestException.restThrow(ErrorType.UNAUTHORIZED);
    }
}
