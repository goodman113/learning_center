package org.example.learningcenter.mapper;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.TimeTableCreateDto;
import org.example.learningcenter.entity.dto.TimeTableUpdateDto;
import org.example.learningcenter.entity.dto.timeTable.TimeTableDto;
import org.example.learningcenter.entity.model.TimeTable;
import org.example.learningcenter.projection.TimeTableProjection;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeTableMapper {
    public TimeTableDto toDto(TimeTable timeTable) {
        return new TimeTableDto(
                timeTable.getId(),
                timeTable.getDays(),
                timeTable.getStartTime(),
                timeTable.getEndTime()
        );
    }

    public TimeTableDto toDtoFromProjection(TimeTableProjection timeTableProjection) {
        return new TimeTableDto(
                timeTableProjection.getId(),
                timeTableProjection.getDays(),
                timeTableProjection.getStartTime(),
                timeTableProjection.getEndTime()
        );
    }

    public TimeTable toEntity(TimeTableCreateDto createDto) {
        return new TimeTable(
                createDto.days(),
                createDto.startTime(),
                createDto.endTime()
        );
    }

    public void update(TimeTable timeTable, TimeTableUpdateDto updateDto) {
        if (updateDto.startTime() != null)
            timeTable.setStartTime(updateDto.startTime());
        if (updateDto.endTime() != null)
            timeTable.setEndTime(updateDto.endTime());
        if (updateDto.days() != null)
            timeTable.setDays(updateDto.days());
    }
}
