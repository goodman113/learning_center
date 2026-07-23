package org.example.learningcenter.controller;

import jakarta.validation.Valid;
import org.example.learningcenter.entity.dto.image.ImageCreateDto;
import org.example.learningcenter.entity.dto.image.ImageDto;
import org.example.learningcenter.entity.dto.image.ImageUpdateDto;
import org.example.learningcenter.service.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<Page<ImageDto>> getAll(
            Pageable pageable,
            @RequestParam(required = false) String search
    ) {
        Page<ImageDto> images = imageService.getAll(pageable, search);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> getById(@PathVariable String id) {
        ImageDto image = imageService.get(id);
        return ResponseEntity.ok(image);
    }

    @PostMapping
    public ResponseEntity<ImageDto> create(@Valid @RequestBody ImageCreateDto createDto) {
        ImageDto createdImage = imageService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageDto> update(
            @PathVariable String id,
            @Valid @RequestBody ImageUpdateDto updateDto
    ) {
        ImageDto updatedImage = imageService.update(updateDto, id);
        return ResponseEntity.ok(updatedImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Optional: Standard Endpoint for Multipart File Uploads to AWS S3
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageDto> uploadFile(@RequestParam("file") MultipartFile file) {
        // You can convert 'file' to ImageCreateDto inside your service or add a custom upload method in ImageService
        // ImageDto uploadedImage = imageService.upload(file);
        // return ResponseEntity.status(HttpStatus.CREATED).body(uploadedImage);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}