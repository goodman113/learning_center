package org.example.learningcenter.entity.dto.group;

import org.example.learningcenter.entity.dto.teacher.TeacherDto;
import org.example.learningcenter.entity.dto.timeTable.TimeTableDto;

public record GroupDto(
        String id,
        String name,
        String room,
        TeacherDto teacher,
        TimeTableDto timeTable
) {
}
