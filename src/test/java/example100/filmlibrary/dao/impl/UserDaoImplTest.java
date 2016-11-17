package example100.filmlibrary.dao.impl;

import example100.filmlibrary.dao.UserDao;
import example100.filmlibrary.entity.User;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
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
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static example100.filmlibrary.testdata.UserFactory.*;
/**
 * Created on 13.11.2016.
 * Time 21:51.
 *
 * @author Volodymyr Portianko
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration("classpath:spring/spring-app.xml")
@ActiveProfiles("dev")
public class UserDaoImplTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private UserDao userDao;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    public void testSave() throws Exception {
        User newUser = getNewUser();
        List<User> expected = getAllUsers();
        expected.add(newUser);

        userDao.save(newUser);
        em.flush();

        USER_MATCHER.assertListEquals(expected, userDao.getAll());
    }

    @Test(expected = PersistenceException.class)
    public void testSaveDuplicateEmail() throws Exception {
        User newUser = getNewUser();
        newUser.setEmail(getUser1().getEmail());

        userDao.save(newUser);
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        User userToUpdate = getUser2();
        userToUpdate.setName("new name");

        userDao.save(userToUpdate);
        em.flush();

        USER_MATCHER.assertEquals(userToUpdate, userDao.get(userToUpdate.getId()));
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        User userToDelete = getUser1();
        List<User> expected = getAllUsers();
        expected.remove(userToDelete);

        Assert.assertTrue(userDao.delete(userToDelete.getId()));
        em.flush();

        USER_MATCHER.assertListEquals(expected, userDao.getAll());
    }

    @Test
    public void testGet() throws Exception {
        User expected = getUser1();

        User actual = userDao.get(expected.getId());

        USER_MATCHER.assertEquals(expected, actual);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User expected = getUser2();

        User actual = userDao.getByEmail(expected.getEmail());

        USER_MATCHER.assertEquals(expected, actual);
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> expected = getAllUsers();

        List<User> actual = userDao.getAll();

        USER_MATCHER.assertListEquals(expected, actual);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    @Parameters({"123", "vvvvv", "site.com", "@com.de", "user@"})
    public void testNotValidEmails(String email) throws Exception {
        User newUser = getNewUser();
        newUser.setEmail(email);

        userDao.save(newUser);
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    @Parameters({"", "nameWith51Symbolaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    public void testNotValidUserName(String userName) throws Exception {
        User newUser = getNewUser();
        newUser.setName(userName);

        userDao.save(newUser);
    }
}