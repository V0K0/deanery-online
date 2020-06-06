package com.vozniuk.springapplication.domain.data.university;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "specialty_id")
    private Integer specialtyId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @NotNull
    @JoinColumn(name = "specialty_name")
    private String specialtyName;

    @NotNull
    @JoinColumn(name = "specialty_code")
    private Integer specialtyCode;

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UniversityGroup> groups;

}
