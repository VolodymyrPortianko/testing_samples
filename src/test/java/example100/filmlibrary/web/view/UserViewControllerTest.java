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
//        User userToGet = UserFactory.getUser1();
//
//        mockMvc.perform(get(USERS_URL + "/" + userToGet.getId())
//                .with(userHttpBasic(UserFactory.getUser2()))
//        )
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.id").value(userToGet.getId()))
//                .andExpect(jsonPath("$.name").value(userToGet.getName()))
//                .andExpect(jsonPath("$.email").value(userToGet.getEmail()))
//                .andExpect(jsonPath("$.password").doesNotExist());
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
                .andExpect(model().attribute("user", USER_MATCHER.getMatcherFor(userToUpdate)));;
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

}