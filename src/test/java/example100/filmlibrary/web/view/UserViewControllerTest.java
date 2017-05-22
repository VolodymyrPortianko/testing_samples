package example100.filmlibrary.web.view;

import example100.filmlibrary.entity.User;
import example100.filmlibrary.testdata.UserFactory;
import example100.filmlibrary.web.AbstractControllerTest;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.springframework.http.MediaType;

import static example100.filmlibrary.testdata.UserFactory.USER_MATCHER;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

/**
 * Created by VP on 20.05.2017.
 */
public class UserViewControllerTest extends AbstractControllerTest {

    private static final String USERS_URL = "/users";

    @Test
    public void userPage() throws Exception {
        User userToOpen = UserFactory.getUser1();

        mockMvc.perform(get(USERS_URL + "/" + userToOpen.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(UserViewController.USER_VIEW))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", USER_MATCHER.getMatcherFor(userToOpen)));
    }

    @Test
    public void userUpdate() throws Exception {
        User userToUpdate = UserFactory.getUser1();
        userToUpdate.setName("UpdatedName");
        userToUpdate.setEmail("111@gmail.com");

        mockMvc.perform(post(USERS_URL + "/" + userToUpdate.getId())
                .with(csrf())
                .with(userFormBased(UserFactory.getUser1()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", userToUpdate.getId().toString())
                .param("name", userToUpdate.getName())
                .param("email", userToUpdate.getEmail())
                .param("password", userToUpdate.getPassword())
                .param("role", userToUpdate.getRole()))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(UserViewController.USER_VIEW))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", USER_MATCHER.getMatcherFor(userToUpdate)));
    }

    @Test
    public void userUpdateNotValid() throws Exception {
        User userToUpdate = UserFactory.getUser1();
        userToUpdate.setEmail("NotValidEmail");

        mockMvc.perform(post(USERS_URL + "/" + userToUpdate.getId())
                .with(csrf())
                .with(userFormBased(UserFactory.getUser1()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", userToUpdate.getId().toString())
                .param("name", userToUpdate.getName())
                .param("email", userToUpdate.getEmail())
                .param("password", userToUpdate.getPassword())
                .param("role", userToUpdate.getRole()))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(UserViewController.USER_VIEW))
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", USER_MATCHER.getMatcherFor(userToUpdate)));;
    }

    @Test
    public void userUpdateNotValidUser() throws Exception {
        User userToUpdate = UserFactory.getUser1();
        userToUpdate.setName("UpdatedName");
        userToUpdate.setEmail("111@gmail.com");

        mockMvc.perform(post(USERS_URL + "/" + userToUpdate.getId())
                .with(csrf())
                .with(userFormBased(UserFactory.getUser2()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", userToUpdate.getId().toString())
                .param("name", userToUpdate.getName())
                .param("email", userToUpdate.getEmail())
                .param("password", userToUpdate.getPassword())
                .param("role", userToUpdate.getRole()))

                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void userUpdateAdmin() throws Exception {
        User userToUpdate = UserFactory.getUser1();
        userToUpdate.setName("UpdatedName");
        userToUpdate.setEmail("111@gmail.com");

        mockMvc.perform(post(USERS_URL + "/" + userToUpdate.getId())
                .with(csrf())
                .with(userFormBased(UserFactory.getAdmin()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", userToUpdate.getId().toString())
                .param("name", userToUpdate.getName())
                .param("email", userToUpdate.getEmail())
                .param("password", userToUpdate.getPassword())
                .param("role", userToUpdate.getRole()))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(UserViewController.USER_VIEW))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", USER_MATCHER.getMatcherFor(userToUpdate)));
    }

    @Test
    public void testNewFormPageAdmin() throws Exception {
        mockMvc.perform(get(USERS_URL + "/new")
                .with(userFormBased(UserFactory.getAdmin())))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(UserViewController.USER_VIEW))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", USER_MATCHER.getMatcherFor(new User())));
    }

    @Test
    public void testNewFormPageNonAdmin() throws Exception {
        mockMvc.perform(get(USERS_URL + "/new")
                .with(userFormBased(UserFactory.getUser1())))

                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testCreateNewUserAdmin() throws Exception {
        User userToCreate = UserFactory.getNewUser();

        mockMvc.perform(post(USERS_URL + "/new")
                .with(csrf())
                .with(userFormBased(UserFactory.getAdmin()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", new String[]{null})
                .param("name", userToCreate.getName())
                .param("email", userToCreate.getEmail())
                .param("password", userToCreate.getPassword())
                .param("role", userToCreate.getRole()))

                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/users/*"));
    }

    @Test
    public void testCreateNewUserNonAdmin() throws Exception {
        User userToCreate = UserFactory.getNewUser();

        mockMvc.perform(post(USERS_URL + "/new")
                .with(csrf())
                .with(userFormBased(UserFactory.getUser1()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", new String[]{null})
                .param("name", userToCreate.getName())
                .param("email", userToCreate.getEmail())
                .param("password", userToCreate.getPassword())
                .param("role", userToCreate.getRole()))

                .andDo(print())
                .andExpect(status().isForbidden());
    }

}