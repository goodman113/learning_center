package org.example.learningcenter.service;

import jakarta.transaction.Transactional;
import org.example.learningcenter.projection.GroupProjection;
import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.dto.group.GroupCreateDto;
import org.example.learningcenter.entity.dto.group.GroupUpdateDto;
import org.example.learningcenter.entity.model.Group;
import org.example.learningcenter.mapper.GroupMapper;
import org.example.learningcenter.repository.GroupRepository;
import org.example.learningcenter.validator.GroupValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Page<GroupProjection> projectionPage = repository.getAllByFilter(search,pageable);
        return projectionPage.
                map(mapper::toDtoFromProjection);

    }

    @Override
    public GroupDto get(String id) {
        Group group = validator.validateIdAndGet(id);
        return mapper.toDto(group);
    }

    @Override
    public GroupDto create(GroupCreateDto createDto) {
        validator.createValid(createDto);
        Group group = mapper.toEntity(createDto);
        return mapper.toDto(repository.save(group));
    }

    @Override
    public GroupDto update(GroupUpdateDto updateDto, String id) {
        Group group = validator.validateIdAndGet(id);
        mapper.mapUpdate(group,updateDto);
        return mapper.toDto(repository.save(group));
    }

    @Override
    @Transactional
    public void delete(String id) {
        Group group = validator.validateIdAndGet(id);
        repository.updateDeleted(group.getId());

    }

    public Integer getCount() {
        Optional<Integer> count = repository.getCount();
        return count.orElse(0);
    }
}
