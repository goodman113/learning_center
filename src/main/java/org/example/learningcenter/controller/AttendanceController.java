package org.example.learningcenter.controller;

import jakarta.validation.Valid;
import org.example.learningcenter.entity.dto.attendance.AttendanceCreateDto;
import org.example.learningcenter.entity.dto.attendance.AttendanceDto;
import org.example.learningcenter.entity.dto.attendance.AttendanceUpdateDto;
import org.example.learningcenter.service.AttendanceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public ResponseEntity<Page<AttendanceDto>> getAll(
            Pageable pageable,
            @RequestParam(required = false) String search
    ) {
        Page<AttendanceDto> attendances = attendanceService.getAll(pageable, search);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getById(@PathVariable String id) {
        AttendanceDto attendance = attendanceService.get(id);
        return ResponseEntity.ok(attendance);
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> create(@Valid @RequestBody AttendanceCreateDto createDto) {
        AttendanceDto createdAttendance = attendanceService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttendance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> update(
            @PathVariable String id,
            @Valid @RequestBody AttendanceUpdateDto updateDto
    ) {
        AttendanceDto updatedAttendance = attendanceService.update(updateDto, id);
        return ResponseEntity.ok(updatedAttendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        attendanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}