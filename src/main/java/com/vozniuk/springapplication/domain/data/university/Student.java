package com.vozniuk.springapplication.domain.data.university;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "group_id")
    private UniversityGroup group;

    private String phone;

}
