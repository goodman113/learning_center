package org.example.learningcenter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentCreateDto;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentDto;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentUpdateDto;
import org.example.learningcenter.service.EnrollmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/enrollment")
public class EnrollmentController {

    final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<Page<EnrollmentDto>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EnrollmentDto> teachers = enrollmentService.getAll(pageable, search);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> count() {
        Long count = enrollmentService.getAllCount();
        return ResponseEntity.ok(Map.of("count",count));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDto> getById(@PathVariable String id) {
        EnrollmentDto teacher = enrollmentService.get(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDto> create(@Valid @RequestBody EnrollmentCreateDto createDto) {
        EnrollmentDto createdTeacher = enrollmentService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDto> update(
            @PathVariable String id,
            @Valid @RequestBody EnrollmentUpdateDto updateDto
    ) {
        EnrollmentDto updatedTeacher = enrollmentService.update(updateDto, id);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
