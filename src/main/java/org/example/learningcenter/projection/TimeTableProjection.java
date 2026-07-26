package org.example.learningcenter.projection;

import org.example.learningcenter.entity.enums.Days;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TimeTableProjection {
    String getId();
    List<Days> getDays();
    LocalTime getStartTime();
    LocalTime getEndTime();
}
