package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends JpaRepository<Organization,String> {
    @Query("select o from Organization o where (:search is null or :search ilike o.name) and o.deleted = false")
    Page<Organization> findAll(@Param("search") String search, Pageable pageable);
}
