package example100.filmlibrary.service.impl;

import example100.filmlibrary.dao.UserDao;
import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.UserService;
import example100.filmlibrary.util.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.isNull;

/**
 * Created on 17.11.2016.
 * Time 17:52.
 *
 * @author Volodymyr Portianko
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User get(int id) {
        User user = userDao.get(id);
        if (isNull(user)) throw new NotFoundException(String.format("User with id=%d is not found", id));
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userDao.getByEmail(email);
        if (isNull(user)) throw new NotFoundException(String.format("User with email=%s is not found", email));
        return user;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void update(User user) {
        User authUser = getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.toString().equals("ROLE_ADMIN"))
                || authUser.getId().equals(user.getId())) {
            userDao.save(user);
        } else throw new AccessDeniedException("This action is not allowed");
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void delete(int id) {
        if (!userDao.delete(id)) throw new NotFoundException(String.format("User with id=%d is not found", id));
    }
}
