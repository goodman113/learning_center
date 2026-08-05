package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.lesson.LessonCreateDto;
import org.example.learningcenter.entity.dto.lesson.LessonDto;
import org.example.learningcenter.entity.dto.lesson.LessonUpdateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Group;
import org.example.learningcenter.entity.model.Lesson;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.mapper.LessonMapper;
import org.example.learningcenter.repository.GroupRepository;
import org.example.learningcenter.repository.TeacherRepository;
import org.example.learningcenter.validator.GroupValidator;
import org.example.learningcenter.validator.LessonValidator;
import org.example.learningcenter.repository.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LessonService extends AbstractService<
        LessonRepository,
        LessonMapper,
        LessonValidator> implements CrudService<LessonCreateDto, LessonUpdateDto, LessonDto, String> {
    final UserService userService;
    final TeacherRepository teacherRepository;
    private final GroupValidator groupValidator;


    protected LessonService(LessonRepository repository, LessonMapper mapper, LessonValidator validator, UserService userService, TeacherRepository teacherRepository, GroupValidator groupValidator) {
        super(repository, mapper, validator);
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.groupValidator = groupValidator;
    }

    @Override
    public Page<LessonDto> getAll(Pageable pageable, String search) {
        Page<Lesson> all = repository.findAll(pageable, search);
        return all.map(mapper::toDto);
    }

    @Override
    public LessonDto get(String id) {
        Lesson lesson = validator.validateIdAndGet(id);
        return mapper.toDto(lesson);
    }

    @Override
    public LessonDto create(LessonCreateDto createDto) {
        validator.validate(createDto);
        Lesson entity = toEntity(createDto);
        Lesson save = repository.save(entity);
        return mapper.toDto(save);
    }

    private Lesson toEntity(LessonCreateDto createDto) {
        Group group = groupValidator.validateIdAndGet(createDto.groupId());
        return new Lesson(
                createDto.lessonName(),
                false,
                group,
                teacherRepository.findTeacherByUser_Id(userService.getCurrentUser().getId()),
                group.getLevel()
        );
    }

    @Override
    public LessonDto update(LessonUpdateDto updateDto, String id) {
        Lesson lesson = validator.validateIdAndGet(id);
        mapper.mapUpdate(lesson, updateDto);
        Lesson save = repository.save(lesson);
        return mapper.toDto(save);
    }

    @Override
    public void delete(String id) {
        Lesson lesson = validator.validateIdAndGet(id);
        lesson.setDeleted(true);
        repository.save(lesson);
    }

    public Long getAllCount() {
        return repository.countLessonsByDeleted(false);
    }


}
