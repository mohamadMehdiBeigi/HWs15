package entity;

import entity.enums.EducationLevel;
import entity.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "students", schema = "hw15")
//@OnDelete(action = OnDeleteAction.CASCADE)
public class Student extends Person{
    @Enumerated
    EducationLevel educationLevel;

    @Column(name = "study_field")
    String studyField;

    @OneToMany(mappedBy = "student")
    List<LessonGrades> lessonGrades;



    public Student(Long aLong, String username, String password, String firstname, String lastname, Gender gender, Long nationalCode, EducationLevel educationLevel, String studyField) {
        super(aLong, username, password, firstname, lastname, gender, nationalCode);
        this.educationLevel = educationLevel;
        this.studyField = studyField;
    }

    public Student(EducationLevel educationLevel, String studyField) {
        this.educationLevel = educationLevel;
        this.studyField = studyField;
    }
}
