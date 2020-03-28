package com.vozniuk.springapplication.domain.data.university;

import com.vozniuk.springapplication.domain.data.university.Department;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "specialty_id")
    private Integer specialtyId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @NotNull
    @JoinColumn(name = "specialty_name")
    private String specialtyName;

    @NotNull
    @JoinColumn(name = "specialty_code")
    private String specialtyCode;

    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UniversityGroup> groups;

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<UniversityGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<UniversityGroup> groups) {
        this.groups = groups;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

}
