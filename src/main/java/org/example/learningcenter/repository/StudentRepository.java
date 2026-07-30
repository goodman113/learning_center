package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Student;
import org.example.learningcenter.projection.StudentProjection;
import org.example.learningcenter.projection.StudentShowProjection;
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
        select s
        from Student s
        join Group g on s.group.id = g.id and g.status = 'ONGOING'
        join Lesson l on l.group.id = g.id and l.isCompleted = true
         where s.deleted = false
        group by s
        having mod(count(l.id), 12) = 0
""")
    List<Student> findAllStudentsForInvoice();

    @Query("SELECT s FROM Student s WHERE s.id IN " +
            "(SELECT e.student.id FROM Enrollment e WHERE e.group.id = :groupId)")
    List<StudentShowProjection> getStudentShowByGroupId(String groupId);
    @Query("""
        select s
        from Student s
        where s.group.id = :groupId
        and s.deleted = false
""")
    List<StudentProjection> getStudentByGroupId(String groupId);
}
