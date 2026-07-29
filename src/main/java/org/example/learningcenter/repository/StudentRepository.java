package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {
    @Query(value = "select s.*, u.phone,u.full_name,u.role from students s JOIN users u on u.id = s.user_id where s.deleted = false and u.full_name ilike concat('%',:search,'%')",
            countQuery = "select count(s.id) from students s JOIN users u on u.id = s.user_id where s.deleted = false and u.full_name ilike concat('%',:search,'%')",
            nativeQuery = true)
    Page<Student> findAll(Pageable pageable, String search);

    Long countStudentsByDeleted(Boolean deleted);

    @Query("""
        select s
        from Student s
        join Group g on s.group.id = g.id and g.status = 'ONGOING'
        join Lesson l on l.group.id = g.id and l.isCompleted = true
         where s.deleted = false
        having mod(count(l.id), 12) = 0
""")
    List<Student> findAllStudentsForInvoice();

}
