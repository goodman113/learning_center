package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.branch.BranchCreateDto;
import org.example.learningcenter.entity.dto.branch.BranchDto;
import org.example.learningcenter.entity.dto.branch.BranchUpdateDto;
import org.example.learningcenter.mapper.BranchMapper;
import org.example.learningcenter.repository.BranchRepository;
import org.example.learningcenter.validator.BranchValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BranchService extends AbstractService<
        BranchRepository,
        BranchMapper,
        BranchValidator> implements CrudService<BranchCreateDto, BranchUpdateDto, BranchDto, String> {

    protected BranchService(BranchRepository repository, BranchMapper mapper, BranchValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Page<BranchDto> getAll(Pageable pageable, String search) {
        return null;
    }

    @Override
    public BranchDto get(String id) {
        return null;
    }

    @Override
    public BranchDto create(BranchCreateDto createDto) {
        return null;
    }

    @Override
    public BranchDto update(BranchUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
