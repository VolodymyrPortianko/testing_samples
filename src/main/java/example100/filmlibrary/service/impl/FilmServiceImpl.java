package example100.filmlibrary.service.impl;

import example100.filmlibrary.dao.FilmDao;
import example100.filmlibrary.entity.Film;
import example100.filmlibrary.service.FilmService;
import example100.filmlibrary.util.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Created on 17.11.2016.
 * Time 23:37.
 *
 * @author Volodymyr Portianko
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmDao filmDao;

    @Override
    @Secured("ROLE_ADMIN")
    public Film save(Film film) {
        return filmDao.save(film);
    }

    @Override
    @Secured("ROLE_ADMIN")
    @Transactional
    public void update(Film film) {
        Film filmToUpdate = filmDao.get(film.getId());
        if (isNull(filmToUpdate)) throw new NotFoundException(String.format("Film with id=%d is not found", film.getId()));
        filmDao.save(film);
    }

    @Override
    public Film get(int id) {
        Film film = filmDao.get(id);
        if (isNull(film)) throw new NotFoundException(String.format("Film with id=%d is not found", id));
        return film;
    }

    @Override
    public Film getByName(String name) {
        Film film = filmDao.getByName(name);
        if (isNull(film)) throw new NotFoundException(String.format("Film with name=%s is not found", name));
        return film;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void delete(int id) {
        if (!filmDao.delete(id)) throw new NotFoundException(String.format("Film with id=%d is not found", id));
    }

    @Override
    public List<Film> getAll() {
        return filmDao.getAll();
    }
}
