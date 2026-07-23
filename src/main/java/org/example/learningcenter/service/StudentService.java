package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.student.StudentDto;
import org.example.learningcenter.entity.dto.student.StudentUpdateDto;
import org.example.learningcenter.entity.dto.student.StudentCreateDto;
import org.example.learningcenter.mapper.StudentMapper;
import org.example.learningcenter.repository.StudentRepository;
import org.example.learningcenter.validator.StudentValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends AbstractService<
        StudentRepository,
        StudentMapper,
        StudentValidator> implements CrudService<StudentCreateDto, StudentUpdateDto, StudentDto,String>{

    protected StudentService(StudentRepository repository, StudentMapper mapper, StudentValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Page<StudentDto> getAll(Pageable pageable, String search) {
        return null;
    }

    @Override
    public StudentDto get(String id) {
        return null;
    }

    @Override
    public StudentDto create(StudentCreateDto createDto) {
        return null;
    }

    @Override
    public StudentDto update(StudentUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
