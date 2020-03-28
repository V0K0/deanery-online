package com.vozniuk.springapplication.domain.data.university;

import com.vozniuk.springapplication.domain.data.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public StudyingPlan getPlan() {
        return plan;
    }

    public void setPlan(StudyingPlan plan) {
        this.plan = plan;
    }
}
