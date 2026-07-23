package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
