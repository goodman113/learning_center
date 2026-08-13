package org.example.learningcenter.repository;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
}
