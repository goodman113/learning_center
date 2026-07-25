package org.example.learningcenter.mapper;

import org.example.learningcenter.entity.dto.image.ImageDto;
import org.example.learningcenter.entity.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageDto toDto(Image image) {
        return new ImageDto(
                image.getId(),
                image.getImageUrl(),
                image.getS3Key(),
                image.getOriginalFileName()
        );
    }
}
