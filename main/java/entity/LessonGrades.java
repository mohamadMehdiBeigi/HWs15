package entity;

import base.model.BaseEntity;
import entity.enums.LessonGradeStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.swing.plaf.TableHeaderUI;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lesson_grades",schema = "hw15")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SequenceGenerator(name = "ID_GENERATOR", allocationSize = 1, sequenceName = "seq_lesson_grades", schema = "hw15")
public class LessonGrades extends BaseEntity<Long> {

    @Range(min = 0, max = 20, message = "digit should be between 1 and 4")
    Double grade;

    @Enumerated(value = EnumType.STRING)
    LessonGradeStatus lessonGradeStatus;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    Lesson lesson;
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    public LessonGrades(Lesson lesson, Student student, Teacher teacher) {
        this.lesson = lesson;
        this.student = student;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "LessonGrades{" +
                "grade=" + grade +
                ", lesson=" + lesson +
                ", student=" + student +
                ", teacher=" + teacher +
                '}';
    }
}
