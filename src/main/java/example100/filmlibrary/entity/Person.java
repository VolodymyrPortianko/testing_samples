package example100.filmlibrary.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created on 13.11.2016.
 * Time 13:27.
 *
 * @author Volodymyr Portianko
 */
@Entity
@Table(name = "persons")
public class Person extends BaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Length(max = 50)
    private String name;

    @Column(name = "surname", nullable = false)
    @NotBlank
    @Length(max = 50)
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    public Person() {
    }

    public Person(Integer id, String name, String surname, LocalDateTime dateOfBirth) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
