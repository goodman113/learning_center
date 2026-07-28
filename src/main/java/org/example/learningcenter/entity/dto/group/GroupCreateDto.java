package org.example.learningcenter.entity.dto.group;

import org.example.learningcenter.entity.enums.GroupStatus;

public record GroupCreateDto(
        String name,
        String room,
        String teacherId,
        String timetableId
) {

}
