package com.vozniuk.springapplication.domain.data.university;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity(name = "university_group")
public class UniversityGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "group_id")
    private Integer groupId;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    @NotNull
    private Specialty specialty;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private StudyingPlan plan;

    @NotNull
    @JoinColumn(name = "group_code")
    private String groupCode;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student> students;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Schedule> schedules;

}
