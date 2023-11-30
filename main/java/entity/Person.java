package entity;

import base.model.BaseEntity;
import entity.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons", schema = "hw15")
@SequenceGenerator(name = "ID_GENERATOR", allocationSize = 1, sequenceName = "seq_person", schema = "hw15")
public class Person extends BaseEntity<Long> {
    @Column(unique = true)
    String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=])(?=[\\S]+$).{4,15}$", message = "password must be between 4 and 15 and should be at least 1 capital letter")
    String password;

    @Pattern(regexp = "[A-Za-z ]+", message = "firstname must have been include just English alphabet")
    String firstname;

    @Pattern(regexp = "[A-Za-z ]+", message = "lastname must have been include just English alphabet")
    String lastname;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "national_code")
    Long nationalCode;

    Boolean status;

    public Person(Long aLong, String username, String password, String firstname, String lastname, Gender gender, Long nationalCode) {
        super(aLong);
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.nationalCode = nationalCode;
    }

    public Person(String username, String password, String firstname, String lastname, Gender gender, Long nationalCode) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.nationalCode = nationalCode;
    }

    @PrePersist
    private void prePersist(){
        this.status = true;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", nationalCode=" + nationalCode +
                '}';
    }
}
