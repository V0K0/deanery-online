package com.vozniuk.deanery.data.university;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "subjectId")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "practical_hours", nullable = false, length = 3)
    private Integer practicalHours;

    @Column(name = "lecture_hours", nullable = false, length = 3)
    private Integer lectureHours;

    @Column(name = "defence_type", nullable = false, length = 20)
    private String defenceType;

    @Column(name = "course_work")
    private boolean courseWork;

    @Column(name = "defence_date")
    private Date defenceDate;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private StudyingPlan plan;

    @Setter(value = AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects")
    private Set<Teacher> teachers = new HashSet<>();

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + subjectId +
                "subjectName='" + subjectName + '\'' +
                ", practicalHours=" + practicalHours +
                ", lectureHours=" + lectureHours +
                ", defenceType='" + defenceType + '\'' +
                ", courseWork=" + courseWork +
                ", defenceDate=" + defenceDate +
                ", plan=" + plan +
                ", teachers=" + teachers.toString() +
                '}';
    }
}
