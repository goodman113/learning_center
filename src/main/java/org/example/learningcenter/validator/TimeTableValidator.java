package org.example.learningcenter.validator;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.TimeTableCreateDto;
import org.example.learningcenter.entity.dto.timeTable.TimeTableDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.TimeTable;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.TimeTableRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeTableValidator {
    final TimeTableRepository repository;
    public TimeTable validateAndGet(String id) {
        return repository.findById(id).orElseThrow(() -> RestException.restThrow(ErrorType.TIMETABLE_NOT_FOUND));
    }

    public void validate(TimeTableCreateDto createDto) {
        if (createDto.endTime().isBefore(createDto.startTime()) || createDto.endTime().equals(createDto.startTime())) {
            throw RestException.restThrow(ErrorType.INVALID_TIME_RANGE);
        }
    }
}
