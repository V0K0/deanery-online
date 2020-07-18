package com.vozniuk.deanery.domain.data.university;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "university_group")
public class UniversityGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_code", nullable = false)
    private String groupCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private StudyingPlan groupPlan;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Schedule> schedules = new HashSet<>();

}
