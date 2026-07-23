package org.example.learningcenter.repository;

import org.example.learningcenter.entity.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,String> {
}
