package example100.filmlibrary.testdata;

import example100.filmlibrary.entity.Person;

import java.time.LocalDate;

/**
 * Created on 17.11.2016.
 * Time 15:08.
 *
 * @author Volodymyr Portianko
 */
public class PersonFactory {

    public static Person getArni() {
        return new Person(1, "Arnold", "Schwarzenegger", LocalDate.parse("1947-07-30"));
    }

    public static Person getHamilton() {
        return new Person(2, "Linda", "Hamilton", LocalDate.parse("1956-09-26"));
    }

    public static Person getCameron() {
        return new Person(3, "James", "Cameron", LocalDate.parse("1954-08-16"));
    }

    public static Person getLucas() {
        return new Person(4, "George", "Lucas", LocalDate.parse("1944-05-14"));
    }

    public static Person getHamill() {
        return new Person(5, "Mark", "Hamill", LocalDate.parse("1951-09-25"));
    }

    public static Person getFord() {
        return new Person(6, "Harrison", "Ford", LocalDate.parse("1942-07-13"));
    }

    public static Person getSpielberg() {
        return new Person(7, "Steven", "Spielberg", LocalDate.parse("1946-12-18"));
    }

    public static Person getCapshaw() {
        return new Person(8, "Kate", "Capshaw", LocalDate.parse("1953-11-03"));
    }

}
