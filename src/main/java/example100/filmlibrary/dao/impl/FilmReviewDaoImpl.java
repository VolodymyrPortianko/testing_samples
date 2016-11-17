package example100.filmlibrary.dao.impl;

import example100.filmlibrary.dao.FilmReviewDao;
import example100.filmlibrary.entity.FilmReview;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created on 17.11.2016.
 * Time 14:32.
 *
 * @author Volodymyr Portianko
 */
@Repository
@Transactional(readOnly = true)
public class FilmReviewDaoImpl implements FilmReviewDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public FilmReview save(FilmReview filmReview) {
        if (filmReview.isNew()) {
            em.persist(filmReview);
            return filmReview;
        } else {
            return em.merge(filmReview);
        }
    }

    @Override
    public FilmReview get(int id) {
        return em.find(FilmReview.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("DELETE FROM FilmReview fr WHERE fr.id=" + id).executeUpdate() != 0;
    }

    @Override
    public List<FilmReview> getByUser(int userId) {
        return em.createQuery("SELECT fr FROM FilmReview fr WHERE fr.user.id=" + userId , FilmReview.class).getResultList();
    }

    @Override
    public List<FilmReview> getByFilm(int filmId) {
        return em.createQuery("SELECT fr FROM FilmReview fr WHERE fr.film.id=" + filmId , FilmReview.class).getResultList();
    }

}
