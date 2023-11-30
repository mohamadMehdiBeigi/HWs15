package entity;

import base.model.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lessons", schema = "hw15", indexes = {
        @Index(
                name = "lesson_index", columnList = "lesson_name, semester, teacher_id", unique = true
        )
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SequenceGenerator(name = "ID_GENERATOR", allocationSize = 1, sequenceName = "seq_lesson", schema = "hw15")
public class Lesson extends BaseEntity<Long> {

    @Column(name = "lesson_name")
    String lessonName;

    @Column(name = "lesson_unit")
    @Range(min = 1, max = 4, message = "digit should be between 1 and 4")
    Integer lessonUnit;
    @Range(min = 1, max = 10, message = "digit should be between 1 and 4")
    Integer semester;

    String field; //university major

    @OneToMany(mappedBy = "lesson")
    List<LessonGrades> lessonGrades;

    @ManyToOne
    Teacher teacher;

}
