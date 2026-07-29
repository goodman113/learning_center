package org.example.learningcenter.validator;

import lombok.RequiredArgsConstructor;
import org.example.learningcenter.entity.enums.ErrorType;
import org.example.learningcenter.entity.model.Invoice;
import org.example.learningcenter.exceptions.RestException;
import org.example.learningcenter.repository.InvoiceRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceValidator {
    final InvoiceRepository repository;

    public Invoice validateIdAndGet(String id) {
        return repository.findById(id)
                .orElseThrow(() -> RestException.restThrow(ErrorType.INVOICE_NOT_FOUND));
    }
}
