package org.example.learningcenter.mapper;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.group.GroupCreateDto;
import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Group;
import org.example.learningcenter.entity.model.Teacher;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.TeacherRepository;
import org.example.learningcenter.repository.TimeTableRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupMapper {
    final TeacherRepository teacherRepository;
    final TimeTableRepository timeTableRepository;
    final TeacherMapper  teacherMapper;
    final TimeTableMapper timeTableMapper;

    public Group toEntity(GroupCreateDto createDto) {
        return new Group(
                createDto.name(),
                createDto.room(),
                teacherRepository.findById(createDto.teacherId()).orElseThrow(() -> RestException.restThrow(ErrorType.TEACHER_NOT_FOUND)),
                timeTableRepository.findById(createDto.timetableId()).orElseThrow(() -> RestException.restThrow(ErrorType.TIMETABLE_NOT_FOUND))
        );
    }

    public GroupDto toDto(Group save) {
        return new GroupDto(
                save.getId(),
                save.getName(),
                save.getRoom(),
                teacherMapper.toDto(save.getTeacher()),
                save.getTimeTable().getId()
        );
    }
}
