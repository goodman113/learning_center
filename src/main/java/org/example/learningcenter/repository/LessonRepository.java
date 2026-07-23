package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,String> {
}
