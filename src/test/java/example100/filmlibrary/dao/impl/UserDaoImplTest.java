package example100.filmlibrary.dao.impl;

import example100.filmlibrary.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created on 13.11.2016.
 * Time 21:51.
 *
 * @author Volodymyr Portianko
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring/spring-app.xml")
@ActiveProfiles("dev")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {
        System.out.println(userDao.get(1));
    }

    @Test
    public void getByEmail() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

}