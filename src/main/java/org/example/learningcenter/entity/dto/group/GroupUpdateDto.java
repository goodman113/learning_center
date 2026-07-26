package org.example.learningcenter.entity.dto.group;

import org.example.learningcenter.entity.enums.GroupStatus;

public record GroupUpdateDto(
        String name,
        String room,
        String teacherId,
        String timeTable,
        GroupStatus status
) {
}
