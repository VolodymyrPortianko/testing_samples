package example100.filmlibrary.dao;

import example100.filmlibrary.entity.FilmReview;

import java.util.List;

/**
 * Created on 13.11.2016.
 * Time 20:48.
 *
 * @author Volodymyr Portianko
 */
public interface FilmReviewDao {

    FilmReview save(FilmReview filmReview);

    FilmReview get(int id);

    boolean delete(int id);

    List<FilmReview> getByUser(int userId);
}
