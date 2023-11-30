package entity;

import entity.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "education_employees", schema = "hw15")
@OnDelete(action = OnDeleteAction.CASCADE)
public class EducationEmployee extends Person {

    @Min(value = 0, message = "your amount of salary must be bigger than 0")
    Double salary;

    public EducationEmployee(String username, String password, String firstname, String lastname, Gender gender, Long nationalCode, Double salary) {
        super(username, password, firstname, lastname, gender, nationalCode);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EducationEmployee{" +
                ", id =  " + id +
                "\n firstname =  " + super.getFirstname() +
                "\n lastname =  " + super.getLastname() +
                "\n salary =  " + salary +
                '}';
    }
}
