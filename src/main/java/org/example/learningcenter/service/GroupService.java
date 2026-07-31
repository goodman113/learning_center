package org.example.learningcenter.service;

import jakarta.transaction.Transactional;
import org.example.learningcenter.entity.dto.group.FullGroupDto;
import org.example.learningcenter.entity.dto.student.StudentDto;
import org.example.learningcenter.entity.enums.Days;
import org.example.learningcenter.entity.enums.GroupStatus;
import org.example.learningcenter.entity.model.TimeTable;
import org.example.learningcenter.projection.GroupNameProjection;
import org.example.learningcenter.projection.GroupProjection;
import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.dto.group.GroupCreateDto;
import org.example.learningcenter.entity.dto.group.GroupUpdateDto;
import org.example.learningcenter.entity.model.Group;
import org.example.learningcenter.mapper.GroupMapper;
import org.example.learningcenter.repository.GroupRepository;
import org.example.learningcenter.validator.GroupValidator;
import org.example.learningcenter.validator.UserValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GroupService extends AbstractService<
        GroupRepository,
        GroupMapper,
        GroupValidator> implements CrudService<GroupCreateDto, GroupUpdateDto, GroupDto, String> {

    private final UserValidator userValidator;
    private final StudentService studentService;

    protected GroupService(GroupRepository repository, GroupMapper mapper, GroupValidator validator, UserValidator userValidator, StudentService studentService) {
        super(repository, mapper, validator);
        this.userValidator = userValidator;
        this.studentService = studentService;
    }

    @Override
    public Page<GroupDto> getAll(Pageable pageable, String search) {
//        Page<GroupProjection> projectionPage = repository.getAllByFilter(search, pageable);
//        return projectionPage.
//                map(mapper::toDtoFromProjection);
        return null;
    }

    public Page<GroupDto> getAll(Pageable pageable, String search, GroupStatus status) {
        Page<GroupProjection> projectionPage = repository.getAllByFilter(search, pageable, status);
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
        mapper.mapUpdate(group, updateDto);
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

    public List<GroupNameProjection> getGroupNames() {
        String userId = userValidator.authenticateAndGetId();
        return repository.findAllGroupNames(userId);
    }

    public FullGroupDto getGroupInfo(String groupId) {
        if (groupId == null) {
            String userId = userValidator.authenticateAndGetId();
            List<Group> allByTeacherId = repository.findAllByTeacherUserId(userId);
            if (allByTeacherId.isEmpty()) {
                return null;
            }
            String today = LocalDate.now().getDayOfWeek().toString().toUpperCase();
            Group group = calculateTimeTable(today, allByTeacherId);
            if (group == null) {
                // try tomorrow
                group = calculateTimeTable(LocalDate.now().plusDays(1).getDayOfWeek().toString().toUpperCase(), allByTeacherId);
            }
            if (group == null) {
                return null;
            }
            GroupDto dto = mapper.toDto(group);
            List<StudentDto> studentsByGroupId = studentService.getStudentsByGroupId(dto.id());
            return new FullGroupDto(studentsByGroupId, dto);
        }
        Group group = validator.validateIdAndGet(groupId);
        GroupDto dto = mapper.toDto(group);
        List<StudentDto> studentsByGroupId = studentService.getStudentsByGroupId(group.getId());
        return new FullGroupDto(studentsByGroupId, dto);
    }

    private Group calculateTimeTable(String today, List<Group> allByTeacherId) {
        Group nearestGroup = null;
        for (Group group : allByTeacherId) {
            TimeTable timeTable = group.getTimeTable();
            if (timeTable.getDays().contains(Days.valueOf(today))) {
                LocalTime startTime = timeTable.getStartTime();
                LocalTime now = LocalTime.now();
                if (startTime.isAfter(now)) {
                    if (nearestGroup == null) {
                        nearestGroup = group;
                    } else {
                        Duration newDiff = Duration.between(startTime, now);
                        Duration currentDiff = Duration.between(nearestGroup.getTimeTable().getStartTime(), now);
                        if (newDiff.compareTo(currentDiff) < 0) nearestGroup = group;
                    }
                }
            }
        }
        return nearestGroup;
    }
}
