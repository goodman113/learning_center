package org.example.learningcenter.projection;

import org.springframework.beans.factory.annotation.Value;

public interface StudentShowProjection {
    String getId();
    String getParentPhone();
    @Value("#{target.user.fullName}")
    String getFullName();
    @Value("#{target.user.phone}")
    String getPhone();
    @Value("#{target.user.imageUrl}")
    String getImageUrl();
}