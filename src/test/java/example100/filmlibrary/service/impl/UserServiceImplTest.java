package example100.filmlibrary.service.impl;

import example100.filmlibrary.dao.UserDao;
import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.AuthService;
import example100.filmlibrary.util.exeption.NotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.Assert.*;

/**
 * Created on 18.11.2016.
 * Time 15:05
 *
 * @author Volodymyr Portianko
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Spy
    @InjectMocks
    private UserServiceImpl sut = new UserServiceImpl();

    @Mock
    private UserDao userDaoMock;

    @Mock
    private AuthService authServiceMock;

    private User testUser = new User();

    @Test
    public void testGet() throws Exception {
        int anyId = 1;
        Mockito.when(userDaoMock.get(anyId)).thenReturn(testUser);

        User actual = sut.get(anyId);

        Mockito.verify(userDaoMock).get(anyId);
        assertSame(testUser, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        int anyId = 1000;
        Mockito.when(userDaoMock.get(anyId)).thenReturn(null);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage(String.format("User with id=%d is not found", anyId));

        sut.get(anyId);
    }

    @Test
    public void testGetByEmail() throws Exception {
        String dummyEmail = "anyEmail";
        Mockito.when(userDaoMock.getByEmail(dummyEmail)).thenReturn(testUser);

        User actual = sut.getByEmail(dummyEmail);

        Mockito.verify(userDaoMock).getByEmail(dummyEmail);
        assertSame(testUser, actual);
    }

    @Test
    public void testGetByEmailNotFound() throws Exception {
        String dummyEmail = "anyEmail";
        Mockito.when(userDaoMock.getByEmail(dummyEmail)).thenReturn(null);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage(String.format("User with email=%s is not found", dummyEmail));

        sut.getByEmail(dummyEmail);
    }

    @Test
    public void testSave() throws Exception {
        Mockito.when(userDaoMock.save(testUser)).thenReturn(testUser);

        User actualUser = sut.save(testUser);

        Mockito.verify(userDaoMock).save(testUser);
        assertSame(testUser, actualUser);
    }

    @Test
    public void testUpdateSameUserAuthenticated() throws Exception {
        testUser.setId(1);
        testUser.setRole("ROLE_USER");
        Mockito.when(authServiceMock.getAuthenticatedUser()).thenReturn(testUser);

        sut.update(testUser);

        Mockito.verify(userDaoMock).save(testUser);
    }

    @Test
    public void testUpdateNotSameUserAuthenticated() throws Exception {
        testUser.setId(1);

        User authUser = new User();
        authUser.setId(2);
        authUser.setRole("ROLE_USER");
        Mockito.when(authServiceMock.getAuthenticatedUser()).thenReturn(authUser);

        expectedException.expect(AccessDeniedException.class);
        expectedException.expectMessage("This action is not allowed");

        sut.update(testUser);
    }

    @Test
    public void testUpdateAdminAuthenticated() throws Exception {
        testUser.setId(1);

        User authUser = new User();
        authUser.setId(2);
        authUser.setRole("ROLE_ADMIN");
        Mockito.when(authServiceMock.getAuthenticatedUser()).thenReturn(authUser);

        sut.update(testUser);

        Mockito.verify(userDaoMock).save(testUser);
    }


    @Test
    public void testDelete() throws Exception {
        int anyId = 1;
        Mockito.when(userDaoMock.delete(anyId)).thenReturn(true);

        sut.delete(anyId);

        Mockito.verify(userDaoMock).delete(anyId);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        int anyId = 100;
        Mockito.when(userDaoMock.delete(anyId)).thenReturn(false);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage(String.format("User with id=%d is not found", anyId));

        sut.delete(anyId);
    }

}