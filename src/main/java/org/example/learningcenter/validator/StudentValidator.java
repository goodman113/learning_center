package org.example.learningcenter.validator;

import lombok.AllArgsConstructor;
import org.example.learningcenter.entity.dto.student.StudentCreateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Student;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentValidator {
    private final StudentRepository repository;

    public Student validateIdAndGet(String id) {
        return repository.findById(id)
                .orElseThrow(()-> RestException.restThrow(ErrorType.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public void validate(StudentCreateDto createDto) {

    }
}
