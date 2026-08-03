package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Image;
import org.example.learningcenter.projection.ImageProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,String> {
    Page<ImageProjection> findAllByUserId(String userId);
}
