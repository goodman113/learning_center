package org.example.learningcenter.validator;

import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Teacher;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TeacherValidator {
    private final TeacherRepository teacherRepository;

    public TeacherValidator(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher validateIdAndGet(String id) {
        return teacherRepository.findById(id)
                .orElseThrow(()-> RestException.restThrow(ErrorType.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public void validate() {

    }
}
