package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.dto.group.GroupCreateDto;
import org.example.learningcenter.entity.dto.group.GroupUpdateDto;
import org.example.learningcenter.mapper.GroupMapper;
import org.example.learningcenter.repository.GroupRepository;
import org.example.learningcenter.validator.GroupValidator;
import org.springframework.data.domain.Pageable;

public class GroupService extends AbstractService<
        GroupRepository,
        GroupMapper,
        GroupValidator> implements CrudService<GroupCreateDto, GroupUpdateDto, GroupDto,String>{

    protected GroupService(GroupRepository repository, GroupMapper mapper, GroupValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Page<GroupDto> getAll(Pageable pageable, String search) {
        return null;
    }

    @Override
    public GroupDto get(String id) {
        return null;
    }

    @Override
    public GroupDto create(GroupCreateDto createDto) {
        return null;
    }

    @Override
    public GroupDto update(GroupUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }> {
}
