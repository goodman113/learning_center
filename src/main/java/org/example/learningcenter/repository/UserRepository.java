package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select * from users where deleted = false and fullname ilike concat('%',:search,'%')",
            countQuery = "select count(id) from users where deleted = false and fullname ilike concat('%',:search,'%')",
            nativeQuery = true)
    Page<User> findAll(Pageable pageable, @Param("search") String search);

    Optional<User> findByPhoneAndDeletedFalse(String phone);

    @Query("select u from User u where u.phone=:phone")
    Optional<User> findByPhone(@Param("phone") String subject);
}
