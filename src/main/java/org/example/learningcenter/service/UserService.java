package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.user.UserCreateDto;
import org.example.learningcenter.entity.dto.user.UserDto;
import org.example.learningcenter.entity.dto.user.UserUpdateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.User;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.mapper.UserMapper;
import org.example.learningcenter.repository.UserRepository;
import org.example.learningcenter.validator.UserValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Page<User> all = repository.findAll(pageable, search);
        return all.map(mapper::toDto);
    }

    @Override
    public UserDto get(String id) {
        User user = validator.validateIdAndGet(id);
        return mapper.toDto(user);
    }

    @Override
    public UserDto create(UserCreateDto createDto) {
        validator.validate(createDto);
        User entity = mapper.toEntity(createDto);
        User save = repository.save(entity);
        return mapper.toDto(save);
    }

    @Override
    public UserDto update(UserUpdateDto updateDto, String id) {
        User user = validator.validateIdAndGet(id);
        mapper.mapUpdate(user,updateDto);
        return mapper.toDto(repository.save(user));
    }

    @Override
    public void delete(String id) {
        User user = validator
                .validateIdAndGet(id);
        user.setDeleted(true);
        repository.save(user);
    }


    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user1 = (UserDetails) auth.getPrincipal();
        return repository.findUserByPhone(user1.getUsername()).orElseThrow(() -> RestException.restThrow(ErrorType.USER_NOT_FOUND));
    }
}
