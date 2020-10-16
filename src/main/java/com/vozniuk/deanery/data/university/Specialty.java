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
public class Specialty extends IndexedEntity {

    @Column(name = "specialty_name", nullable = false)
    private String specialtyName;

    @Column(name = "specialty_code", nullable = false)
    private Integer specialtyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL)
    private Set<UniversityGroup> groups = new HashSet<>();

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                "specialtyName='" + specialtyName + '\'' +
                ", specialtyCode=" + specialtyCode +
                ", department=" + department.getDepartmentName() +
                ", groups=" + groups.toString() +
                '}';
    }
}
