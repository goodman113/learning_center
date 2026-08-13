package org.example.learningcenter.service;

import jakarta.transaction.Transactional;
import org.example.learningcenter.entity.dto.branch.BranchCreateDto;
import org.example.learningcenter.entity.dto.branch.BranchDto;
import org.example.learningcenter.entity.dto.branch.BranchUpdateDto;
import org.example.learningcenter.entity.model.Branch;
import org.example.learningcenter.entity.model.Organization;
import org.example.learningcenter.entity.model.User;
import org.example.learningcenter.exceptions.ErrorCodes;
import org.example.learningcenter.exceptions.ErrorType;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.mapper.BranchMapper;
import org.example.learningcenter.repository.BranchRepository;
import org.example.learningcenter.repository.OrganizationRepository;
import org.example.learningcenter.validator.BranchValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BranchService extends AbstractService<
        BranchRepository,
        BranchMapper,
        BranchValidator> implements CrudService<BranchCreateDto, BranchUpdateDto, BranchDto, String> {

    final OrganizationRepository organizationRepository;
    protected BranchService(BranchRepository repository, BranchMapper mapper, BranchValidator validator, OrganizationRepository organizationRepository) {
        super(repository, mapper, validator);
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Page<BranchDto> getAll(Pageable pageable, String search) {
        Page<Branch> branches = repository.findAll(search,pageable);
        return branches.map(mapper::toDto);
    }

    @Override
    public BranchDto get(String id) {
        Branch branch = repository.findById(id).orElseThrow(() -> new RestException(ErrorType.BRANCH_NOT_FOUND, ErrorCodes.NotFound));
        return mapper.toDto(branch);
    }

    @Override
    public BranchDto create(BranchCreateDto createDto) {
        validator.validate(createDto.name());
        Organization organization = organizationRepository.findById(createDto.organizationId())
                .orElseThrow(() -> new RestException(ErrorType.ORGANIZATION_NOT_FOUND, ErrorCodes.NotFound));
        Branch branch = mapper.toEntity(createDto, organization);
        Branch save = repository.save(branch);
        return mapper.toDto(save);
    }

    @Override
    @Transactional
    public BranchDto update(BranchUpdateDto updateDto, String id) {
        Branch branch =validator.validateIdAndGet(id);
        validator.validate(updateDto.name());
        mapper.updateEntity(branch, updateDto);
        Branch save = repository.save(branch);
        return mapper.toDto(save);

    }

    @Override
    public void delete(String id) {
        validator.validateIdAndGet(id);
        repository.deleteByIdFalse(id);
    }

    public Long getAllCount(User user) {

        /// add organization filter
        return null;


    }
}
