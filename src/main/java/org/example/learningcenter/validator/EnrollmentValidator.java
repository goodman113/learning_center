package org.example.learningcenter.validator;

import lombok.AllArgsConstructor;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Enrollment;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.EnrollmentRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EnrollmentValidator {
    private final EnrollmentRepository repository;

    public void validateId(String id) {
        Boolean exists = repository.checkId(id).orElse(false);
        if (!exists) {
            throw RestException.restThrow(ErrorType.ENROLLMENT_NOT_FOUND);
        }
    }

    public Enrollment validateIdAndGet(String id) {
        return repository.findById(id)
                .orElseThrow(() -> RestException.restThrow(ErrorType.ENROLLMENT_NOT_FOUND));
    }
}
