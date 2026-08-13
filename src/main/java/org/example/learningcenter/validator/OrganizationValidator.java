package org.example.learningcenter.validator;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.organization.OrganizationCreateDto;
import org.example.learningcenter.entity.dto.organization.OrganizationUpdateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Organization;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.OrganizationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationValidator {
    private final OrganizationRepository repository;


    public Organization validateAndGetId(String id) {
        return repository.findById(id)
                .orElseThrow((() -> RestException.restThrow(ErrorType.ORGANIZATION_NOT_FOUND));
    }

    public void validate(OrganizationCreateDto createDto) {


    }

    public void validate(OrganizationUpdateDto updateDto) {


    }
}
