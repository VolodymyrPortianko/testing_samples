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

    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    @Transactional
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetByUser() throws Exception {

    }

    @Test
    public void testGetByFilm() throws Exception {

    }

    @Test
    public void testRatingValidValues() throws Exception {

    }

    @Test
    public void testRatingNotValidValues() throws Exception {

    }
}