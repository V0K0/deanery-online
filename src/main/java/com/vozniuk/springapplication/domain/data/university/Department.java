package com.vozniuk.springapplication.domain.data.university;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "department_id")
    private Integer departmentId;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @NotNull
    private Faculty faculty;

    @NotNull
    @JoinColumn(name = "department_name")
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Specialty> specialties;

}
