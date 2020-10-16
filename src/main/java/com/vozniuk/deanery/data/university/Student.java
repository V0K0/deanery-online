package com.vozniuk.deanery.data.university;

import com.vozniuk.deanery.data.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Student {

    @Id
    private Long id;

    private String name;

    private String lastname;

    private String patronymic;

    @JoinColumn(name = "date_of_birth")
    private Date dateOfBirth;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private UniversityGroup studentGroup;

    private String phone;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User account;

    public Student(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", studentGroup=" + studentGroup.getGroupCode() +
                ", phone='" + phone + '\'' +
                '}';
    }

}
