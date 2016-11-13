package example100.filmlibrary.dao;

import example100.filmlibrary.entity.Film;

import java.util.List;

/**
 * Created on 13.11.2016.
 * Time 20:48.
 *
 * @author Volodymyr Portianko
 */
public interface FilmDao {

    Film save(Film film);

    Film get(int id);

    Film getByName(String name);

    boolean delete(int id);

    List<Film> getAll();
}
