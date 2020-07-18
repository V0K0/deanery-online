package com.vozniuk.deanery.domain.data.university;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "practical_hours", nullable = false)
    private Integer practicalHours;

    @Column(name = "lecture_hours", nullable = false)
    private Integer lectureHours;

    @Column(name = "defence_type", nullable = false)
    private String defenceType;

    @Column(name = "course_work")
    private boolean courseWork;

    @Future
    @Column(name = "defence_date")
    private Date defenceDate;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private StudyingPlan plan;

    @Setter(value = AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
    private Set<Teacher> teachers = new HashSet<>();

}
