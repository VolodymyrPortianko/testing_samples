package example100.filmlibrary.service;

import example100.filmlibrary.entity.FilmReview;

import java.util.List;

/**
 * Created on 17.11.2016.
 * Time 17:51.
 *
 * @author Volodymyr Portianko
 */
public interface FilmReviewService {

    FilmReview save(FilmReview filmReview);

    void update(FilmReview filmReview);

    FilmReview get(int id);

    void delete(int id);

    List<FilmReview> getByUser(int userId);

    List<FilmReview> getByFilm(int filmId);
}
