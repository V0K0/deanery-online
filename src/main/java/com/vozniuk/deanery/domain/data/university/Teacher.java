package com.vozniuk.deanery.domain.data.university;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer teacherId;

    private String name;

    private String lastname;

    private String patronymic;

    private String phone;

    @Setter(value = AccessLevel.PRIVATE)
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Subject.class)
    @JoinTable(name = "subject_teacher_relation",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();

}
