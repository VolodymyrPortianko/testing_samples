package example100.filmlibrary.service;

import example100.filmlibrary.entity.User;

/**
 * Created on 17.11.2016.
 * Time 17:51.
 *
 * @author Volodymyr Portianko
 */
public interface UserService {

    User get(int id);

    User getByEmail(String email);

    User save(User user);

    void update(User user);

    void delete(int id);
}
