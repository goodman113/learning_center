package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.lesson.LessonCreateDto;
import org.example.learningcenter.entity.dto.lesson.LessonDto;
import org.example.learningcenter.entity.dto.lesson.LessonUpdateDto;
import org.example.learningcenter.mapper.LessonMapper;
import org.example.learningcenter.mapper.LessonValidator;
import org.example.learningcenter.repository.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LessonService extends AbstractService<
        LessonRepository,
        LessonMapper,
        LessonValidator> implements CrudService<LessonCreateDto, LessonUpdateDto, LessonDto,String>{

    protected LessonService(LessonRepository repository, LessonMapper mapper, LessonValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Page<LessonDto> getAll(Pageable pageable, String search) {
        return null;
    }

    @Override
    public LessonDto get(String id) {
        return null;
    }

    @Override
    public LessonDto create(LessonCreateDto createDto) {
        return null;
    }

    @Override
    public LessonDto update(LessonUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
