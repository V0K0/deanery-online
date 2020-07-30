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
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialty_id")
    private Integer specialtyId;

    @Column(name = "specialty_name", nullable = false)
    private String specialtyName;

    @Column(name = "specialty_code", nullable = false)
    private Integer specialtyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL)
    private Set<UniversityGroup> groups = new HashSet<>();

}
