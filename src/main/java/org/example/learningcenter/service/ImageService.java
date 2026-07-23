package org.example.learningcenter.service;

import org.example.learningcenter.entity.dto.image.ImageCreateDto;
import org.example.learningcenter.entity.dto.image.ImageDto;
import org.example.learningcenter.entity.dto.image.ImageUpdateDto;
import org.example.learningcenter.mapper.ImageMapper;
import org.example.learningcenter.repository.ImageRepository;
import org.example.learningcenter.validator.ImageValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ImageService extends AbstractService<
        ImageRepository,
        ImageMapper,
        ImageValidator> implements CrudService<ImageCreateDto, ImageUpdateDto, ImageDto,String>{

    protected ImageService(ImageRepository repository, ImageMapper mapper, ImageValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Page<ImageDto> getAll(Pageable pageable, String search) {
        return null;
    }

    @Override
    public ImageDto get(String id) {
        return null;
    }

    @Override
    public ImageDto create(ImageCreateDto createDto) {
        return null;
    }

    @Override
    public ImageDto update(ImageUpdateDto updateDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
