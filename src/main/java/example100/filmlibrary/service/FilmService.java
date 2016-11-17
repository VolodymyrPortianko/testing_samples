package example100.filmlibrary.service;

import example100.filmlibrary.entity.Film;

import java.util.List;

/**
 * Created on 17.11.2016.
 * Time 17:51.
 *
 * @author Volodymyr Portianko
 */
public interface FilmService {

    Film save(Film film);

    void update(Film film);

    Film get(int id);

    Film getByName(String name);

    void delete(int id);

    List<Film> getAll();
}
