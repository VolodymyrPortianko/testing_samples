package example100.filmlibrary.service.impl;

import example100.filmlibrary.dao.FilmReviewDao;
import example100.filmlibrary.entity.FilmReview;
import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.AuthService;
import example100.filmlibrary.service.FilmReviewService;
import example100.filmlibrary.util.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
    private AuthService authService;

    @Override
    @Transactional
    public FilmReview save(FilmReview filmReview) {
        User authUser = authService.getAuthenticatedUser();
        filmReview.setUser(authUser);
        return filmReviewDao.save(filmReview);
    }

    @Override
    @Transactional
    public void update(FilmReview filmReview) {
        User authUser = authService.getAuthenticatedUser();
        FilmReview reviewToUpdate = filmReviewDao.get(filmReview.getId());

        if (isNull(reviewToUpdate)) throw new NotFoundException(String.format("Film review with id=%d is not found", filmReview.getId()));

        if (authUser.getRole().equals("ROLE_ADMIN") || authUser.getId().equals(reviewToUpdate.getUser().getId())) {
            filmReviewDao.save(filmReview);
        } else throw new AccessDeniedException("This action is not allowed");
    }

    @Override
    public FilmReview get(int id) {
        FilmReview filmReview = filmReviewDao.get(id);
        if (isNull(filmReview)) throw new NotFoundException(String.format("Film review with id=%d is not found", id));
        return filmReview;
    }

    @Override
    @Transactional
    public void delete(int id) {
        User authUser = authService.getAuthenticatedUser();
        FilmReview reviewToDelete = filmReviewDao.get(id);

        if (isNull(reviewToDelete)) throw new NotFoundException(String.format("Film review with id=%d is not found", id));

        if (authUser.getRole().equals("ROLE_ADMIN") || authUser.getId().equals(reviewToDelete.getUser().getId())) {
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
