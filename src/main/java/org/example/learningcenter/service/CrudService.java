package org.example.learningcenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<CD, UD, D, I> {

    Page<D> getAll(Pageable pageable, String search);

    D get(I id);

    D create(CD createDto);

    D update(UD updateDto, I id);

    void delete(I id);
}
