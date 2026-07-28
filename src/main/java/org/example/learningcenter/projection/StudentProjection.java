package org.example.learningcenter.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface StudentProjection {
    String getId();

    @Value("#{target.user.id}")
    String getUserId();

    @Value("#{target.user.fullName}")
    String getFullName();

    @Value("#{target.user.phone}")
    String getPhone();

    @Value("#{target.user.birthDate}")
    LocalDate getBirthDate();

    String getParentPhone();
}