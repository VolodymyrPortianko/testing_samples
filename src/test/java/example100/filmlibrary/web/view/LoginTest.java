package example100.filmlibrary.web.view;

import example100.filmlibrary.entity.User;
import example100.filmlibrary.testdata.UserFactory;
import example100.filmlibrary.web.AbstractControllerTest;
import org.junit.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Volodymyr Portianko
 * @since 22.05.2017
 */
public class LoginTest extends AbstractControllerTest {

    @Test
    public void successfulLoginTest() throws Exception {
        User userToLogin = UserFactory.getUser2();

        mockMvc.perform(
                formLogin("/login").user(userToLogin.getEmail()).password(userToLogin.getPassword()))
                .andDo(print())
                .andExpect(authenticated().withUsername(userToLogin.getEmail()).withRoles("USER"))
                .andExpect(redirectedUrl("/films"));

    }

    @Test
    public void successfulAdminLoginTest() throws Exception {
        User adminToLogin = UserFactory.getAdmin();

        mockMvc.perform(
                formLogin("/login").user(adminToLogin.getEmail()).password(adminToLogin.getPassword()))
                .andDo(print())
                .andExpect(authenticated().withUsername(adminToLogin.getEmail()).withRoles("ADMIN"))
                .andExpect(redirectedUrl("/films"));

    }

    @Test
    public void unsuccessfulLoginTest() throws Exception {
        User userToLogin = UserFactory.getUser2();

        mockMvc.perform(
                formLogin("/login").user(userToLogin.getEmail()).password("invalidPassword"))
                .andDo(print())
                .andExpect(unauthenticated())
                .andExpect(redirectedUrl("/login?error=true"));

    }
}
