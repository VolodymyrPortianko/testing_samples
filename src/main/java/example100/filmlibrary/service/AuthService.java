package example100.filmlibrary.service;

import example100.filmlibrary.entity.User;

/**
 * Created on 22.11.2016.
 * Time 21:04.
 *
 * @author Volodymyr Portianko
 */
public interface AuthService {

    User getAuthenticatedUser();
}
