package org.example.learningcenter.mapper;

import org.example.learningcenter.entity.annotation.IgnoreAuditFields;
import org.example.learningcenter.entity.dto.student.StudentCreateDto;
import org.example.learningcenter.entity.dto.student.StudentDto;
import org.example.learningcenter.entity.dto.student.StudentUpdateDto;
import org.example.learningcenter.entity.model.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StudentMapper {

    @Mapping(source = "user", target = "userDto")
    StudentDto toDto(Student student);

    Student toEntity(StudentCreateDto studentDto);

    @IgnoreAuditFields
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUpdate(@MappingTarget Student student, StudentUpdateDto updateDto);
}