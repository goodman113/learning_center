package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Student;
import org.example.learningcenter.projection.StudentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("""
                SELECT s FROM Student s
                JOIN s.user u
                WHERE s.deleted = false
                  AND u.deleted = false
                  AND (:search IS NULL OR :search = '' OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :search, '%')))
            """)
    Page<StudentProjection> searchStudents(@Param("search") String search, Pageable pageable);

    Long countStudentsByDeleted(Boolean deleted);

    @Query("""
            SELECT s FROM Student s
                JOIN s.user u
                WHERE s.deleted = false
                  AND u.deleted = false
                  AND s.id in (select e.student.id from Enrollment e where e.group.id=:groupId)
            """)
    List<StudentProjection> getStudentByGroupId(@Param("groupId") String groupId);
}
