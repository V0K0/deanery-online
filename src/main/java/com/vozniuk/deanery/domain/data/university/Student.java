package com.vozniuk.deanery.domain.data.university;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
public class Student {

    @Id
    private Integer id;

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

}
