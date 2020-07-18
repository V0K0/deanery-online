package com.vozniuk.deanery.domain.data.university;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Integer facultyId;


    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private Set<Department> departments = new HashSet<>();

}
