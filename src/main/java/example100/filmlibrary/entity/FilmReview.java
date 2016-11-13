package example100.filmlibrary.entity;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
    @Column(name = "review")
    private String review;
    @Column(name = "rating")
    private Integer rating;

    public FilmReview() {
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
