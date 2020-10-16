package com.vozniuk.deanery.data.university;

import com.vozniuk.deanery.data.IndexedEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "studying_plan")
@EqualsAndHashCode(of = "id")
public class StudyingPlan extends IndexedEntity {

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "groupPlan", cascade = CascadeType.PERSIST)
    private Set<UniversityGroup> universityGroups = new HashSet<>();

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

    @Override
    public String toString() {
        return "StudyingPlan{" +
                "id=" + id +
                "universityGroups=" + universityGroups +
                ", subjects=" + subjects.toString() +
                '}';
    }
}
