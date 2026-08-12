package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, String> {

}
