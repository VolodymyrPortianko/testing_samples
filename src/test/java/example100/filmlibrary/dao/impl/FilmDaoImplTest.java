package example100.filmlibrary.dao.impl;

import example100.filmlibrary.dao.FilmDao;
import example100.filmlibrary.entity.Film;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import java.util.List;

import static example100.filmlibrary.testdata.FilmFactory.*;

/**
 * Created on 17.11.2016.
 * Time 15:04.
 *
 * @author Volodymyr Portianko
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration("classpath:spring/spring-app.xml")
@ActiveProfiles("dev")
public class FilmDaoImplTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private FilmDao filmDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void testSave() throws Exception {
        Film newFilm = getNewFilm();
        List<Film> expectedFilms = getAllFilms();
        expectedFilms.add(newFilm);

        filmDao.save(newFilm);
        em.flush();

        FILM_MATCHER.assertListEquals(expectedFilms, filmDao.getAll());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Film updatedFilm = getUpdatedTerminatorFilm();

        filmDao.save(updatedFilm);
        em.flush();

        FILM_MATCHER.assertEquals(updatedFilm, filmDao.get(updatedFilm.getId()));
    }

    @Test
    public void testGet() throws Exception {
        Film expected = getStarWarsFilm();

        Film actual = filmDao.get(expected.getId());

        FILM_MATCHER.assertEquals(expected, actual);
    }

    @Test
    public void testGetByName() throws Exception {
        Film expected = getStarWarsFilm();

        Film actual = filmDao.getByName(expected.getName());

        FILM_MATCHER.assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        Film filmToDelete = getIndianaJonesFilm();
        List<Film> expectedFilms = getAllFilms();
        expectedFilms.remove(filmToDelete);

        Assert.assertTrue(filmDao.delete(filmToDelete.getId()));
        em.flush();

        FILM_MATCHER.assertListEquals(expectedFilms, filmDao.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Film> expectedFilms = getAllFilms();

        List<Film> actualFilms = filmDao.getAll();

        FILM_MATCHER.assertListEquals(expectedFilms, actualFilms);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void testDirectorNotNull() throws Exception {
        Film newFilm = getNewFilm();
        newFilm.setDirector(null);

        filmDao.save(newFilm);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void testActorsNotEmpty() throws Exception {
        Film newFilm = getNewFilm();
        newFilm.getActors().clear();

        filmDao.save(newFilm);
    }

}