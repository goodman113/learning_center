package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
