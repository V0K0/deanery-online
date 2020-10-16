package com.vozniuk.deanery.data.university;

import com.vozniuk.deanery.data.IndexedEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "university_group")
@EqualsAndHashCode(of = "id")
public class UniversityGroup extends IndexedEntity {

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

    public void addStudent(Student student) {
        student.setStudentGroup(this);
        students.add(student);
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    @Override
    public String toString() {
        return "UniversityGroup{" +
                "id=" + id +
                "groupCode='" + groupCode + '\'' +
                ", specialty=" + specialty.getSpecialtyName() +
                ", groupPlan=" + groupPlan.getId() +
                ", students=" + students +
                ", schedules=" + schedules +
                '}';
    }

}
