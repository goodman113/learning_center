package org.example.learningcenter.entity.dto.branch;

import org.example.learningcenter.entity.model.Organization;

import java.math.BigDecimal;

public record BranchCreateDto(
        String organizationId,
        BigDecimal chargeForMonth,
        String name,
        String address,
        String googlePlaceId,
        Double latitude,
        Double longitude,
        String googleMapsUrl
        ) {
}
