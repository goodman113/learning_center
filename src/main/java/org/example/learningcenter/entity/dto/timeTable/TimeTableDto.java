package org.example.learningcenter.entity.dto.timeTable;

import org.example.learningcenter.entity.enums.Days;

import java.time.LocalTime;
import java.util.List;

public record TimeTableDto(
        List<Days> days,
        LocalTime startTime,
        LocalTime endTime
) {
}
