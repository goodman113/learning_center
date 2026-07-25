package org.example.learningcenter.validator;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.group.GroupCreateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.GroupRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupValidator {
    final GroupRepository repository;
    public void createValid(GroupCreateDto createDto) {
        if (repository.existsGroupByName(createDto.name())) {
            throw RestException.restThrow(ErrorType.GROUP_ALREADY_EXISTS_WITH_THIS_NAME);
        }

    }
}
