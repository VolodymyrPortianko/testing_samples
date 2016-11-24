package example100.filmlibrary.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name of film should not be blank")
    @Length(max = 50, message = "Name of film should contain only 50 chars max")
    private String name;

    @Column(name = "genre")
    @Enumerated(EnumType.ORDINAL)
    private Genre genre;

    @Column(name = "description")
    @Length(max = 255, message = "Description is too long. Max 255 chars")
    private String description;

    @ManyToOne
    @NotNull(message = "Every film should have a director")
    @JoinColumn(name = "director_id")
    private Person director;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_actors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    @Size(min = 1, message = "Film should have at least 1 actor")
    private List<Person> actors = new ArrayList<>();

    public Film() {
    }

    public Film(Integer id, String name, Genre genre, String description, Person director, List<Person> actors) {
        super(id);
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.director = director;
        this.actors = actors;
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
