package org.example.learningcenter.validator;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.user.UserCreateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.User;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository repository;


    public User validateIdAndGet(String id) {
        return repository.findById(id)
                .orElseThrow(()-> RestException.restThrow(ErrorType.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public void validate(UserCreateDto createDto) {

    }
}
