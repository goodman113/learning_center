package org.example.learningcenter.validator;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.ImageRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageValidator {
    private final ImageRepository repository;

    public void validateId(String id) {
        Boolean exists = repository.checkId(id).orElse(false);
        if (!exists){
            throw RestException.restThrow(ErrorType.ATTACHMENT_NOT_FOUND);
        }
    }
}
