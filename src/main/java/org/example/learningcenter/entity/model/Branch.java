package org.example.learningcenter.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.learningcenter.entity.base.BaseEntity;
import org.example.learningcenter.entity.dto.user.UserCreateDto;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Branch  extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    private BigDecimal chargeForMonth;


}
