package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student,String> {
    @Query(value = "select s.*, u.phone,u.full_name,u.role from students s JOIN users u on u.id = s.user_id where s.deleted = false and u.full_name ilike concat('%',:search,'%')",
            countQuery = "select count(s.id) from students s JOIN users u on u.id = s.user_id where s.deleted = false and u.full_name ilike concat('%',:search,'%')",
            nativeQuery = true)
    Page<Student> findAll(Pageable pageable, String search);
}
