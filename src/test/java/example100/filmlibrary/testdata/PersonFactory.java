package example100.filmlibrary.testdata;

import example100.filmlibrary.entity.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created on 17.11.2016.
 * Time 15:08.
 *
 * @author Volodymyr Portianko
 */
public class PersonFactory {

    public static Person getArni() {
        return new Person(1, "Arnold", "Schwarzenegger", LocalDateTime.parse("1947-07-30T00:00:00"));
    }

    public static Person getHamilton() {
        return new Person(2, "Linda", "Hamilton", LocalDateTime.parse("1956-09-26T00:00:00"));
    }

    public static Person getCameron() {
        return new Person(3, "James", "Cameron", LocalDateTime.parse("1954-08-16T00:00:00"));
    }

    public static Person getLucas() {
        return new Person(4, "George", "Lucas", LocalDateTime.parse("1944-05-14T00:00:00"));
    }

    public static Person getHamill() {
        return new Person(5, "Mark", "Hamill", LocalDateTime.parse("1951-09-25T00:00:00"));
    }

    public static Person getFord() {
        return new Person(6, "Harrison", "Ford", LocalDateTime.parse("1942-07-13T00:00:00"));
    }

    public static Person getSpielberg() {
        return new Person(7, "Steven", "Spielberg", LocalDateTime.parse("1946-12-18T00:00:00"));
    }

    public static Person getCapshaw() {
        return new Person(8, "Kate", "Capshaw", LocalDateTime.parse("1953-11-03T00:00:00"));
    }

}
