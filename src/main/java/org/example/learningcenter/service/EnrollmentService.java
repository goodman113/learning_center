package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.enrollment.EnrollmentCreateDto;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentDto;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentUpdateDto;
import org.example.learningcenter.mapper.EnrollmentMapper;
import org.example.learningcenter.repository.EnrollmentRepository;
import org.example.learningcenter.validator.EnrollmentValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService extends AbstractService<
        EnrollmentRepository,
        EnrollmentMapper,
        EnrollmentValidator> implements CrudService<EnrollmentCreateDto, EnrollmentUpdateDto, EnrollmentDto,String>{

    protected EnrollmentService(EnrollmentRepository repository, EnrollmentMapper mapper, EnrollmentValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Page<EnrollmentDto> getAll(Pageable pageable, String search) {
        return null;
    }

    @Override
    public EnrollmentDto get(String id) {
        return null;
    }

    @Override
    public EnrollmentDto create(EnrollmentCreateDto createDto) {
        return null;
    }

    @Override
    public EnrollmentDto update(EnrollmentUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
