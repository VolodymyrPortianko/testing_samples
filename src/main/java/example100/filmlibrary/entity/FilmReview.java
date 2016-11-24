package example100.filmlibrary.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created on 13.11.2016.
 * Time 19:53.
 *
 * @author Volodymyr Portianko
 */
@Entity
@Table(name = "user_film_reviews")
public class FilmReview extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    @NotNull(message = "You should specify film for review")
    private Film film;

    @Column(name = "review")
    @Length(max = 1024, message = "Review is too long. Max 1024 chars")
    private String review;

    @Column(name = "rating")
    @Range(min = 0, max = 10, message = "Rating should be a number with value from 0 to 10")
    private Integer rating;

    public FilmReview() {
    }

    public FilmReview(Integer id, User user, Film film, String review, Integer rating) {
        super(id);
        this.user = user;
        this.film = film;
        this.review = review;
        this.rating = rating;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FilmReview{" +
                "id=" + id +
                ", film=" + film +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
