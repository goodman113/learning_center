package org.example.learningcenter.entity.dto.student;

import org.example.learningcenter.entity.dto.user.UserDto;

public record StudentDto(UserDto userDto,
                         String parentPhone) {
}
