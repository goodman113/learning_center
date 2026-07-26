package org.example.learningcenter.entity.dto.teacher;

import org.example.learningcenter.entity.dto.user.UserDto;

public record TeacherDto(
        UserDto userDto
) {
}
