package com.vozniuk.deanery.data.university;

import com.vozniuk.deanery.data.IndexedEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
public class Faculty extends IndexedEntity {

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private Set<Department> departments = new HashSet<>();

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                "facultyName='" + facultyName + '\'' +
                ", departments=" + departments.toString() +
                '}';
    }
}
