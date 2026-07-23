package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
}
