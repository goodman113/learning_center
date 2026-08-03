package org.example.learningcenter.validator;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.dto.enrollment.EnrollmentUpdateDto;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Enrollment;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.EnrollmentRepository;
import org.example.learningcenter.repository.GroupRepository;
import org.example.learningcenter.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentValidator {
    final EnrollmentRepository enrollmentRepository;
    final StudentRepository studentRepository;
    final GroupRepository groupRepository;
    public Enrollment validateAndGet(String id) {
        return enrollmentRepository.findById(id).orElseThrow
                (()-> RestException.restThrow(ErrorType.ENROLLMENT_NOT_FOUND));
    }

    public Enrollment validateAndPutValue(EnrollmentUpdateDto updateDto, String id) {
        Enrollment enrollment = validateAndGet(id);
        if (updateDto.studentId() != null)
            enrollment.setStudent(studentRepository.findById(updateDto.studentId()).orElseThrow(()-> RestException.restThrow(ErrorType.STUDENT_NOT_FOUND)));
        if (updateDto.groupId() != null)
            enrollment.setGroup(groupRepository.findById(updateDto.groupId()).orElseThrow(()-> RestException.restThrow(ErrorType.GROUP_NOT_FOUND)));
        return enrollmentRepository.save(enrollment);
    }
}
