package org.example.learningcenter.mapper;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.lesson.LessonCreateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Lesson;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.LessonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonValidator {
    private final LessonRepository repository;

    public Lesson validateIdAndGet(String id) {
        return repository.findById(id)
                .orElseThrow(()-> RestException.restThrow(ErrorType.LESSON_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public void validate(LessonCreateDto createDto) {

    }
}
