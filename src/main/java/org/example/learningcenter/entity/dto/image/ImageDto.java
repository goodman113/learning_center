package org.example.learningcenter.entity.dto.image;

public record ImageDto(
        String id,
        String imageUrl,
        String s3Key,
        String originalFileName
) {
}
