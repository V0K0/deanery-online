package com.vozniuk.springapplication.domain.data.university;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
            targetEntity = Teacher.class)
    @JoinTable(name = "subject_teacher_relation",
            joinColumns = @JoinColumn(name = "teacher_id",
                    nullable = false,
                    updatable = false),
            inverseJoinColumns = @JoinColumn(name = "subject_id",
                    nullable = false,
                    updatable = false),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<Teacher> teachers = new HashSet<>();

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getPracticalHours() {
        return practicalHours;
    }

    public void setPracticalHours(Integer practicalHours) {
        this.practicalHours = practicalHours;
    }

    public Integer getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(Integer lectureHours) {
        this.lectureHours = lectureHours;
    }

    public String getDefenceType() {
        return defenceType;
    }

    public void setDefenceType(String defenceType) {
        this.defenceType = defenceType;
    }

    public boolean hasCourseWork() {
        return courseWork;
    }

    public void setCourseWork(boolean courseWork) {
        this.courseWork = courseWork;
    }

    public StudyingPlan getPlan() {
        return plan;
    }

    public void setPlan(StudyingPlan plan) {
        this.plan = plan;
    }

    public Date getDefenceDate() {
        return defenceDate;
    }

    public void setDefenceDate(Date defenceDate) {
        this.defenceDate = defenceDate;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
