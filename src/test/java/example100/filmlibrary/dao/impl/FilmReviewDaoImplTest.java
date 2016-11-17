package example100.filmlibrary.dao.impl;

import example100.filmlibrary.dao.FilmReviewDao;
import example100.filmlibrary.entity.FilmReview;
import example100.filmlibrary.testdata.FilmFactory;
import example100.filmlibrary.testdata.UserFactory;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
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

import static example100.filmlibrary.testdata.FilmReviewFactory.*;

/**
 * Created on 17.11.2016.
 * Time 15:05.
 *
 * @author Volodymyr Portianko
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration("classpath:spring/spring-app.xml")
@ActiveProfiles("dev")
public class FilmReviewDaoImplTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private FilmReviewDao filmReviewDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void testSave() throws Exception {
        FilmReview newFilmReview = getNewTerminatorReview();
        List<FilmReview> expected = getAllReviewsOfTerminatorFilm();
        expected.add(newFilmReview);

        filmReviewDao.save(newFilmReview);
        em.flush();

        FR_MATCHER.assertListEquals(expected, filmReviewDao.getByFilm(newFilmReview.getFilm().getId()));
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        FilmReview updatedFilmReview = getUpdatedIndianaJonesReview1();

        filmReviewDao.save(updatedFilmReview);
        em.flush();

        FR_MATCHER.assertEquals(updatedFilmReview, filmReviewDao.get(updatedFilmReview.getId()));
    }

    @Test
    public void testGet() throws Exception {
        FilmReview expected = getIndianaJonesReview2();

        FilmReview actual = filmReviewDao.get(expected.getId());

        FR_MATCHER.assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        FilmReview filmReviewToDelete = getStarWarsReview1();
        List<FilmReview> expected = getAllReviewsOfStarWarsFilm();
        expected.remove(filmReviewToDelete);

        filmReviewDao.delete(filmReviewToDelete.getId());
        em.flush();

        FR_MATCHER.assertListEquals(expected, filmReviewDao.getByFilm(filmReviewToDelete.getFilm().getId()));
    }

    @Test
    public void testGetByUser() throws Exception {
        List<FilmReview> expected = getAllReviewsOfUser1();

        List<FilmReview> actual = filmReviewDao.getByUser(UserFactory.getUser1().getId());

        FR_MATCHER.assertListEquals(expected, actual);
    }

    @Test
    public void testGetByFilm() throws Exception {
        List<FilmReview> expected = getAllReviewsOfStarWarsFilm();

        List<FilmReview> actual = filmReviewDao.getByFilm(FilmFactory.getStarWarsFilm().getId());

        FR_MATCHER.assertListEquals(expected, actual);
    }

    @Test
    @Transactional
    @Parameters({"0","1","2","3","4","5","6","7","8","9","10"})
    public void testRatingValidValues(int rating) throws Exception {
        FilmReview newFilmReview = getNewTerminatorReview();
        newFilmReview.setRating(rating);

        filmReviewDao.save(newFilmReview);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    @Parameters({"-100","-1","-1000000","11","12","100","10000000"})
    public void testRatingNotValidValues(int rating) throws Exception {
        FilmReview newFilmReview = getNewTerminatorReview();
        newFilmReview.setRating(rating);

        filmReviewDao.save(newFilmReview);
    }
}