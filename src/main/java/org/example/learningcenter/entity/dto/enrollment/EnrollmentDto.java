package org.example.learningcenter.entity.dto.enrollment;

import org.example.learningcenter.entity.dto.group.GroupDto;
import org.example.learningcenter.entity.dto.student.StudentDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EnrollmentDto(
        StudentDto student,
        GroupDto group,
        LocalDateTime enrollmentDate
) {

}
