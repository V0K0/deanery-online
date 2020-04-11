package com.vozniuk.springapplication.domain.data.university;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @JoinColumn(name = "teacher_id")
    private Integer teacherId;

    private String name;

    private String lastname;

    private String patronymic;

    private String phone;

    @OneToMany(mappedBy = "teacher")
    private Set<Subject> teacherSubjects;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Subject> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(Set<Subject> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }


}
