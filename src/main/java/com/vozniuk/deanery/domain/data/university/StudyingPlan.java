package com.vozniuk.deanery.domain.data.university;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "studying_plan")
public class StudyingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Integer planId;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "groupPlan",cascade = CascadeType.PERSIST)
    private Set<UniversityGroup> universityGroup = new HashSet<>();

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

}
