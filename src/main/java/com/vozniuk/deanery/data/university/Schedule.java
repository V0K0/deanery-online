package com.vozniuk.deanery.data.university;

import com.vozniuk.deanery.data.IndexedEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
public class Schedule extends IndexedEntity {

    @Column(nullable = false, length = 2)
    private Integer term;

    @Column(name = "day_of_week", length = 20)
    private String dayOfWeek;

    @Column(name = "week_type", length = 20)
    private String weekType;

    @Column(name = "lesson_time")
    private Time lessonTime;

    @Column(name = "lesson_place")
    private String lessonPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private UniversityGroup group;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                "term=" + term +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", weekType='" + weekType + '\'' +
                ", lessonTime=" + lessonTime +
                ", lessonPlace='" + lessonPlace + '\'' +
                ", group=" + group.getGroupCode() +
                ", subject=" + subject.getSubjectName() +
                '}';
    }
}
