package example100.filmlibrary.dao;

import example100.filmlibrary.entity.User;

import java.util.List;

/**
 * Created on 13.11.2016.
 * Time 20:47.
 *
 * @author Volodymyr Portianko
 */
public interface UserDao {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}
