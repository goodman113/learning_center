package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,String> {
}
