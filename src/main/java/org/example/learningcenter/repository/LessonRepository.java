package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LessonRepository extends JpaRepository<Lesson,String> {
    @Query(value = "select l from Lesson l where l.deleted = false and (:search is null or l.teacher.user.fullName ilike concat('%',cast(:search as string),'%'))")
    Page<Lesson> findAll(Pageable pageable, @Param("search") String search);

    Long countLessonsByDeleted(Boolean deleted);
}
