package com.vozniuk.deanery.data.university;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "teacherId")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    private String patronymic;

    private String phone;

    @Setter(value = AccessLevel.PRIVATE)
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Subject.class)
    @JoinTable(name = "subject_teacher_relation",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + teacherId +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + phone + '\'' +
                ", subjects=" + subjects.toString() +
                '}';
    }
}
