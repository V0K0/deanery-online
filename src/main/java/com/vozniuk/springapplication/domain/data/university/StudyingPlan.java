package com.vozniuk.springapplication.domain.data.university;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "studying_plan")
public class StudyingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "plan_id")
    private Integer planId;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UniversityGroup> universityGroup;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Subject> subjects;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Set<UniversityGroup> getUniversityGroup() {
        return universityGroup;
    }

    public void setUniversityGroup(Set<UniversityGroup> universityGroup) {
        this.universityGroup = universityGroup;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }


}
