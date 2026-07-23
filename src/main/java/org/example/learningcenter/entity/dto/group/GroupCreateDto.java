package org.example.learningcenter.entity.dto.group;

public record GroupCreateDto(
        String name,
        String room,
        String teacherId,
        String timetableId
) {

}
