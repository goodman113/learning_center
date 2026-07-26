package org.example.learningcenter.entity.dto;

import org.example.learningcenter.entity.enums.Days;

import java.time.LocalTime;
import java.util.List;

public record TimeTableCreateDto(
        List<Days> days,
        LocalTime startTime,
        LocalTime endTime
) {
}
