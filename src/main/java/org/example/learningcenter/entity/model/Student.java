package org.example.learningcenter.entity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.example.learningcenter.entity.base.BaseEntity;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {


    private String parentPhone;

    @ManyToOne
    private Group group;

    @OneToOne(optional = false)
    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;
}