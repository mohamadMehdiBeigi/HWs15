package entity;

import entity.enums.Gender;
import entity.enums.TeacherLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "teachers", schema = "hw15")
public class Teacher extends Person{

    @Column(name = "teacher_level")
    @Enumerated(EnumType.STRING)
    TeacherLevel teacherLevel;


    public Teacher(String username, String password, String firstname, String lastname, Gender gender, Long nationalCode, TeacherLevel teacherLevel) {
        super(username, password, firstname, lastname, gender, nationalCode);
        this.teacherLevel = teacherLevel;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", firstname='" + super.getFirstname() + '\'' +
                ", lastname='" + super.getLastname() + '\'' +
                ", gender=" + super.getGender() +
                ", nationalCode=" + super.getNationalCode() +
                "teacherLevel=" + teacherLevel +
                '}';
    }
}
