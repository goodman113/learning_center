package org.example.learningcenter.service;

import jakarta.transaction.Transactional;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentCreateDto;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentDto;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentUpdateDto;
import org.example.learningcenter.entity.model.Enrollment;
import org.example.learningcenter.mapper.EnrollmentMapper;
import org.example.learningcenter.projection.EnrollmentProjection;
import org.example.learningcenter.repository.EnrollmentRepository;
import org.example.learningcenter.validator.EnrollmentValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Page<EnrollmentProjection> enrollmentProjection = repository.findEnrollmentsBySearch(search,pageable);
         return enrollmentProjection.map(mapper::toDtoFromProjection);
    }

    @Override
    public EnrollmentDto get(String id) {
        Enrollment enrollment = validator.validateAndGet(id);
        return mapper.toDto(enrollment);
    }

    @Override
    public EnrollmentDto create(EnrollmentCreateDto createDto) {
        Enrollment enrollment = mapper.toEntityFromCreate(createDto);

        return mapper.toDto(repository.save(enrollment));
    }

    @Override
    @Transactional
    public EnrollmentDto update(EnrollmentUpdateDto updateDto, String id) {
        Enrollment enrollment = validator.validateAndPutValue(updateDto, id);
        return mapper.toDto(enrollment);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Enrollment enrollment = validator.validateAndGet(id);
        repository.updateDeleted(id);
    }

    public Long getAllCount() {
        return repository.countEnrollmentsByDeleted(false);
    }
}
