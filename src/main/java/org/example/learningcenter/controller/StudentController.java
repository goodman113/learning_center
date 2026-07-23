package org.example.learningcenter.controller;

import jakarta.validation.Valid;
import org.example.learningcenter.entity.dto.student.StudentCreateDto;
import org.example.learningcenter.entity.dto.student.StudentDto;
import org.example.learningcenter.entity.dto.student.StudentUpdateDto;
import org.example.learningcenter.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Page<StudentDto>> getAll(
            Pageable pageable,
            @RequestParam(required = false) String search
    ) {
        Page<StudentDto> students = studentService.getAll(pageable, search);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable String id) {
        StudentDto student = studentService.get(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<StudentDto> create(@Valid @RequestBody StudentCreateDto createDto) {
        StudentDto createdStudent = studentService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(
            @PathVariable String id,
            @Valid @RequestBody StudentUpdateDto updateDto
    ) {
        StudentDto updatedStudent = studentService.update(updateDto, id);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}