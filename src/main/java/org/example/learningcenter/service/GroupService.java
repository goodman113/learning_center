package org.example.learningcenter.service;

import org.example.learningcenter.Projection.GroupProjection;
import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.dto.group.GroupCreateDto;
import org.example.learningcenter.entity.dto.group.GroupUpdateDto;
import org.example.learningcenter.entity.model.Group;
import org.example.learningcenter.mapper.GroupMapper;
import org.example.learningcenter.repository.GroupRepository;
import org.example.learningcenter.validator.GroupValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        validator.createValid(createDto);
        Group group = mapper.toEntity(createDto);
        return mapper.toDto(repository.save(group));
    }

    @Override
    public GroupDto update(GroupUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    public List<GroupDto> getAll(String name, String room, String teacher, String timeTable, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GroupProjection> projectionPage = repository.getAllByFilter(name,room,teacher,timeTable,pageable);
        return null;
    }
}
