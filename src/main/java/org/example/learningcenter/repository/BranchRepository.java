package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {

    @Query("""
            select b from Branch b
            where b.deleted = false and
            ( :search is null
            or b.name ilike concat('%', :search, '%')
            or b.address ilike concat('%', :search, '%'))
""")
    Page<Branch> findAll(String search, Pageable pageable);

    boolean existsBranchByName(String name);

    @Modifying
    @Query("UPDATE Branch b SET b.deleted = true WHERE b.id = :id")
    void deleteByIdFalse(String id);

}
