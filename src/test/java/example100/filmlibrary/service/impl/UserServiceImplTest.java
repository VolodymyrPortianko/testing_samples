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

    @Mock
    private User dummyUser;

    @Test
    public void testGet() throws Exception {
        int anyId = 1;
        Mockito.when(userDaoMock.get(anyId)).thenReturn(dummyUser);

        User actual = sut.get(anyId);

        Mockito.verify(userDaoMock).get(anyId);
        assertSame(dummyUser, actual);
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
        Mockito.when(userDaoMock.getByEmail(dummyEmail)).thenReturn(dummyUser);

        User actual = sut.getByEmail(dummyEmail);

        Mockito.verify(userDaoMock).getByEmail(dummyEmail);
        assertSame(dummyUser, actual);
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
        Mockito.when(userDaoMock.save(dummyUser)).thenReturn(dummyUser);

        User actualUser = sut.save(dummyUser);

        Mockito.verify(userDaoMock).save(dummyUser);
        assertSame(dummyUser, actualUser);
    }

    @Test
    public void testUpdateSameUserAuthenticated() throws Exception {
        Mockito.when(dummyUser.getId()).thenReturn(1);
        Mockito.when(dummyUser.getRole()).thenReturn("ROLE_USER");
        Mockito.when(authServiceMock.getAuthenticatedUser()).thenReturn(dummyUser);

        sut.update(dummyUser);

        Mockito.verify(userDaoMock).save(dummyUser);
    }

    @Test
    public void testUpdateNotSameUserAuthenticated() throws Exception {
        Mockito.when(dummyUser.getId()).thenReturn(1);

        User authUser = Mockito.mock(User.class);
        Mockito.when(authUser.getId()).thenReturn(2);
        Mockito.when(authUser.getRole()).thenReturn("ROLE_USER");
        Mockito.when(authServiceMock.getAuthenticatedUser()).thenReturn(authUser);

        expectedException.expect(AccessDeniedException.class);
        expectedException.expectMessage("This action is not allowed");

        sut.update(dummyUser);
    }

    @Test
    public void testUpdateAdminAuthenticated() throws Exception {
        Mockito.when(dummyUser.getId()).thenReturn(1);

        User authUser = Mockito.mock(User.class);
        Mockito.when(authUser.getId()).thenReturn(2);
        Mockito.when(authUser.getRole()).thenReturn("ROLE_ADMIN");
        Mockito.when(authServiceMock.getAuthenticatedUser()).thenReturn(authUser);

        sut.update(dummyUser);

        Mockito.verify(userDaoMock).save(dummyUser);
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