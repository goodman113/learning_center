package org.example.learningcenter.service;

import jakarta.validation.Valid;
import org.example.learningcenter.entity.dto.organization.OrganizationCreateDto;
import org.example.learningcenter.entity.dto.organization.OrganizationDto;
import org.example.learningcenter.entity.dto.organization.OrganizationUpdateDto;
import org.example.learningcenter.entity.dto.user.UserCreateDto;
import org.example.learningcenter.entity.enums.Role;
import org.example.learningcenter.entity.model.Organization;
import org.example.learningcenter.mapper.OrganizationMapper;
import org.example.learningcenter.repository.OrganizationRepository;
import org.example.learningcenter.validator.OrganizationValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService extends AbstractService<
        OrganizationRepository,
        OrganizationMapper,
        OrganizationValidator> implements CrudService<OrganizationCreateDto, OrganizationUpdateDto, OrganizationDto, String> {

    private final UserService userService;
    private final Generator generator;

    protected OrganizationService(OrganizationRepository repository, OrganizationMapper mapper, OrganizationValidator validator, UserService userService, Generator generator) {
        super(repository, mapper, validator);
        this.userService = userService;
        this.generator = generator;
    }

    @Override
    public Page<OrganizationDto> getAll(Pageable pageable, String search) {
        Page<Organization> all = repository.findAll(search, pageable);
        return all.map(mapper::toDto);
    }

    @Override
    public OrganizationDto get(String id) {
        Organization organization = validator.validateAndGetId(id);
        return mapper.toDto(organization);
    }

    @Override
    public OrganizationDto create(OrganizationCreateDto createDto) {
        validator.validate(createDto);
        Organization entity = mapper.toEntity(createDto);
        OrganizationDto dto = mapper.toDto(repository.save(entity));
        userService.create(new UserCreateDto(generator.generateSuperAdminName(createDto.name()),null,null, Role.SUPER_ADMIN));
        return dto;
    }

    @Override
    public OrganizationDto update(OrganizationUpdateDto updateDto, String id) {
        validator.validate(updateDto);
        Organization organization = validator.validateAndGetId(id);
        mapper.mapUpdate(organization,updateDto);
        return mapper.toDto(repository.save(organization));
    }

    @Override
    public void delete(String id) {

    }
}
