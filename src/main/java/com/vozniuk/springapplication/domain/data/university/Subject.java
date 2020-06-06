package com.vozniuk.springapplication.domain.data.university;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "subject_id")
    private Integer subjectId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private StudyingPlan plan;

    @NotNull
    @JoinColumn(name = "subject_name")
    private String subjectName;

    @NotNull
    @JoinColumn(name = "practical_hours")
    private Integer practicalHours;

    @NotNull
    @JoinColumn(name = "lecture_hours")
    private Integer lectureHours;

    @NotNull
    @JoinColumn(name = "defence_type")
    private String defenceType;

    @JoinColumn(name = "course_work")
    private boolean courseWork;

    @JoinColumn(name = "defence_date")
    private Date defenceDate;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            mappedBy = "subjects")
    private Set<Teacher> teachers = new HashSet<>();

}
