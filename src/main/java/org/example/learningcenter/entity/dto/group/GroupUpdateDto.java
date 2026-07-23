package org.example.learningcenter.entity.dto.group;

public record GroupUpdateDto(
        String name,
        String room,
        String teacherId,
        String timeTable
) {
}
