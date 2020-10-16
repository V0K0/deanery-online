package com.vozniuk.deanery.data.university;

import com.vozniuk.deanery.data.IndexedEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
public class Department extends IndexedEntity {

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Specialty> specialties = new HashSet<>();

    @Override
    public String toString() {
        return "Department{" +
                "id =" + id +
                "departmentName='" + departmentName + '\'' +
                ", faculty=" + faculty.getFacultyName() +
                '}';
    }
}
