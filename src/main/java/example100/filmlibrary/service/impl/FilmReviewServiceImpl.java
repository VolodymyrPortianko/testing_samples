package example100.filmlibrary.service.impl;

import example100.filmlibrary.dao.FilmReviewDao;
import example100.filmlibrary.entity.FilmReview;
import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.FilmReviewService;
import example100.filmlibrary.service.UserService;
import example100.filmlibrary.util.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Created on 17.11.2016.
 * Time 23:46.
 *
 * @author Volodymyr Portianko
 */
@Service
public class FilmReviewServiceImpl implements FilmReviewService {

    @Autowired
    private FilmReviewDao filmReviewDao;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public FilmReview save(FilmReview filmReview) {
        User authUser = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        filmReview.setUser(authUser);
        return filmReviewDao.save(filmReview);
    }

    @Override
    @Transactional
    public void update(FilmReview filmReview) {
        User authUser = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        FilmReview reviewToUpdate = filmReviewDao.get(filmReview.getId());
        if (isNull(reviewToUpdate)) throw new NotFoundException(String.format("Film review with id=%d is not found", filmReview.getId()));

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.toString().equals("ROLE_ADMIN"))
                || authUser.getId().equals(reviewToUpdate.getId())) {
            filmReviewDao.save(filmReview);
        } else throw new AccessDeniedException("This action is not allowed");
    }

    @Override
    public FilmReview get(int id) {
        return filmReviewDao.get(id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User authUser = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        FilmReview reviewToDelete = filmReviewDao.get(id);
        if (isNull(reviewToDelete)) throw new NotFoundException(String.format("Film review with id=%d is not found", id));

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.toString().equals("ROLE_ADMIN"))
                || authUser.getId().equals(reviewToDelete.getId())) {
            filmReviewDao.delete(id);
        } else throw new AccessDeniedException("This action is not allowed");
    }

    @Override
    public List<FilmReview> getByUser(int userId) {
        return filmReviewDao.getByUser(userId);
    }

    @Override
    public List<FilmReview> getByFilm(int filmId) {
        return filmReviewDao.getByFilm(filmId);
    }
}
