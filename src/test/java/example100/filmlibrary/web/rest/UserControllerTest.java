package example100.filmlibrary.web.rest;

import example100.filmlibrary.entity.User;
import example100.filmlibrary.testdata.UserFactory;
import example100.filmlibrary.web.AbstractControllerTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created on 17.11.2016.
 * Time 19:06.
 *
 * @author Volodymyr Portianko
 */
public class UserControllerTest extends AbstractControllerTest {

    private static final String USERS_URL = "/rest/users";

    @Test
    public void testGet() throws Exception {
        User userToGet = UserFactory.getUser1();

        mockMvc.perform(get(USERS_URL + "/" + userToGet.getId())
                .with(userHttpBasic(UserFactory.getUser2()))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(userToGet.getId()))
                .andExpect(jsonPath("$.name").value(userToGet.getName()))
                .andExpect(jsonPath("$.email").value(userToGet.getEmail()))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    public void testGetByEmail() throws Exception {
        User userToGet = UserFactory.getUser2();

        mockMvc.perform(get(USERS_URL + "/by?email=" + userToGet.getEmail())
                .with(userHttpBasic(UserFactory.getUser1()))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(userToGet.getId()))
                .andExpect(jsonPath("$.name").value(userToGet.getName()))
                .andExpect(jsonPath("$.email").value(userToGet.getEmail()))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(USERS_URL + "/1000")
                .with(userHttpBasic(UserFactory.getUser1()))
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewUser() throws Exception {
        User userToSave = UserFactory.getNewUser();

        mockMvc.perform(post(USERS_URL)
                .content(objectMapper.writeValueAsString(userToSave))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(userHttpBasic(UserFactory.getAdmin()))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Location", CoreMatchers.notNullValue()))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(userToSave.getName()))
                .andExpect(jsonPath("$.email").value(userToSave.getEmail()))
                .andExpect(jsonPath("$.password").doesNotExist());

    }

    @Test
    public void testCreateNotEnoughRights() throws Exception {
        User userToSave = UserFactory.getNewUser();

        mockMvc.perform(post(USERS_URL)
                .content(objectMapper.writeValueAsString(userToSave))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(userHttpBasic(UserFactory.getUser1()))
        )
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDelete() throws Exception {
        User userToDelete = UserFactory.getUser1();

        mockMvc.perform(delete(USERS_URL + "/" + userToDelete.getId())
                .with(userHttpBasic(UserFactory.getAdmin()))
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(USERS_URL + "/1000")
                .with(userHttpBasic(UserFactory.getAdmin()))
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteNotEnoughRights() throws Exception {
        mockMvc.perform(delete(USERS_URL + "/1000")
                .with(userHttpBasic(UserFactory.getUser1()))
        )
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdate() throws Exception {
        User userToUpdate = UserFactory.getUser1();
        userToUpdate.setName("new name");

        mockMvc.perform(put(USERS_URL + "/" + userToUpdate.getId())
                .content(objectMapper.writeValueAsString(userToUpdate))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(userHttpBasic(userToUpdate))
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAdmin() throws Exception {
        User userToUpdate = UserFactory.getUser1();
        userToUpdate.setName("new name");

        mockMvc.perform(put(USERS_URL + "/" + userToUpdate.getId())
                .content(objectMapper.writeValueAsString(userToUpdate))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(userHttpBasic(UserFactory.getAdmin()))
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNotOwner() throws Exception {
        User userToUpdate = UserFactory.getUser1();
        userToUpdate.setName("new name");

        mockMvc.perform(put(USERS_URL + "/" + userToUpdate.getId())
                .content(objectMapper.writeValueAsString(userToUpdate))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(userHttpBasic(UserFactory.getUser2()))
        )
                .andDo(print())
                .andExpect(status().isForbidden());
    }


}