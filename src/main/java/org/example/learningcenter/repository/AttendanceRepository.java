package org.example.learningcenter.repository;

import jakarta.transaction.Transactional;
import org.example.learningcenter.entity.model.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,String> {
    @Query("select a from Attendance a where a.deleted = false and (:search is null or a.lesson.lessonName ilike :search)")
    Page<Attendance> findAll(Pageable pageable, @Param("search") String search);

    @Query("select exists (select t from Attendance t where t.id =:id)")
    Optional<Boolean> checkId(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("update Attendance t set t.deleted = true where t.id =:id")
    void softDelete(String id);
}
