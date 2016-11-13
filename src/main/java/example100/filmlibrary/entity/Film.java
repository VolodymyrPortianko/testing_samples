package example100.filmlibrary.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created on 13.11.2016.
 * Time 13:26.
 *
 * @author Volodymyr Portianko
 */
@Entity
@Table(name = "films")
public class Film extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "genre")
    @Enumerated(EnumType.ORDINAL)
    private Genre genre;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Person director;
    @ManyToMany
    @JoinTable(name = "film_actors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> actors;

    public Film() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", description='" + description + '\'' +
                ", director=" + director +
                ", actors=" + actors +
                '}';
    }
}
