package example100.filmlibrary.dao.impl;

import example100.filmlibrary.dao.FilmDao;
import example100.filmlibrary.entity.Film;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created on 17.11.2016.
 * Time 14:31.
 *
 * @author Volodymyr Portianko
 */
@Repository
@Transactional(readOnly = true)
public class FilmDaoImpl implements FilmDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Film save(Film film) {
        if (film.isNew()) {
            em.persist(film);
            return film;
        } else {
            return em.merge(film);
        }
    }

    @Override
    public Film get(int id) {
        return em.find(Film.class, id);
    }

    @Override
    public Film getByName(String name) {
        return em.createQuery("SELECT f FROM Film f WHERE f.name='" + name + "'", Film.class).getSingleResult();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("DELETE FROM Film f WHERE f.id=" + id).executeUpdate() != 0;
    }

    @Override
    public List<Film> getAll() {
        return em.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }
}
