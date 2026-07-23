package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.teacher.TeacherCreateDto;
import org.example.learningcenter.entity.dto.teacher.TeacherDto;
import org.example.learningcenter.entity.dto.teacher.TeacherUpdateDto;
import org.example.learningcenter.mapper.TeacherMapper;
import org.example.learningcenter.repository.TeacherRepository;
import org.example.learningcenter.validator.TeacherValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends AbstractService<
        TeacherRepository,
        TeacherMapper,
        TeacherValidator> implements CrudService<TeacherCreateDto, TeacherUpdateDto, TeacherDto,String>{

    protected TeacherService(TeacherRepository repository, TeacherMapper mapper, TeacherValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Page<TeacherDto> getAll(Pageable pageable, String search) {
        return null;
    }

    @Override
    public TeacherDto get(String id) {
        return null;
    }

    @Override
    public TeacherDto create(TeacherCreateDto createDto) {
        return null;
    }

    @Override
    public TeacherDto update(TeacherUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
