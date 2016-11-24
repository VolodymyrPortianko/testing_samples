package example100.filmlibrary.service.impl;

import example100.filmlibrary.dao.UserDao;
import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created on 22.11.2016.
 * Time 21:04.
 *
 * @author Volodymyr Portianko
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getAuthenticatedUser() {
        return userDao.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
