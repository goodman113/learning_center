package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.user.UserCreateDto;
import org.example.learningcenter.entity.dto.user.UserDto;
import org.example.learningcenter.entity.dto.user.UserUpdateDto;
import org.example.learningcenter.mapper.UserMapper;
import org.example.learningcenter.repository.UserRepository;
import org.example.learningcenter.validator.UserValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<
        UserRepository,
        UserMapper,
        UserValidator> implements CrudService<UserCreateDto, UserUpdateDto, UserDto,String>{

    protected UserService(UserRepository repository, UserMapper mapper, UserValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable, String search) {
        return null;
    }

    @Override
    public UserDto get(String id) {
        return null;
    }

    @Override
    public UserDto create(UserCreateDto createDto) {
        return null;
    }

    @Override
    public UserDto update(UserUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
