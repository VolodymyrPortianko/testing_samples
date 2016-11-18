package example100.filmlibrary.service.impl;

import example100.filmlibrary.dao.UserDao;
import example100.filmlibrary.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created on 18.11.2016.
 * Time 15:05
 *
 * @author Volodymyr Portianko
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Spy
    @InjectMocks
    private UserServiceImpl sut = new UserServiceImpl();

    @Mock
    private UserDao userDaoMock;

    @Mock
    private User dummyUser;

    @Test
    public void testGet() throws Exception {
        Mockito.when(userDaoMock.get(1)).thenReturn(dummyUser);

        User actual = sut.get(1);

        Mockito.verify(userDaoMock).get(1);
        assertEquals(dummyUser, actual);
    }

    @Test
    public void testGetByEmail() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

}