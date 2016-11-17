package example100.filmlibrary.testdata;

import com.google.common.collect.Lists;
import example100.filmlibrary.entity.Film;
import example100.filmlibrary.entity.Genre;

import java.util.List;

import static example100.filmlibrary.testdata.PersonFactory.*;

/**
 * Created on 17.11.2016.
 * Time 15:05.
 *
 * @author Volodymyr Portianko
 */
public class FilmFactory {

    public static final ModelMatcher<Film, String> FILM_MATCHER = new ModelMatcher<>(Film::toString);

    private static final String TERMINATOR_DESCRIPTION = "A cyborg, identical to the one who failed to kill Sarah Connor," +
            " must now protect her young son, John Connor, from a more advanced cyborg, made out of liquid metal.";
    private static final String STAR_WARS_DESCRIPTION = "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot," +
            " a wookiee and two droids to save the galaxy from the Empire's world-destroying battle-station, while " +
            "also attempting to rescue Princess Leia from the evil Darth Vader.";
    private static final String INDIANA_JONES_DESCRIPTION = "After arriving in India, Indiana Jones is asked by a desperate " +
            "village to find a mystical stone. He agrees, and stumbles upon a secret cult plotting a terrible plan" +
            " in the catacombs of an ancient palace.";

    public static Film getTerminatorFilm() {
        return new Film(1,
                "Terminator 2: Judgment Day",
                Genre.THRILLER,
                TERMINATOR_DESCRIPTION,
                getCameron(),
                Lists.newArrayList(getArni(), getHamilton()));
    }

    public static Film getStarWarsFilm() {
        return new Film(2,
                "Star Wars: Episode IV - A New Hope",
                Genre.FANTASY,
                STAR_WARS_DESCRIPTION,
                getLucas(),
                Lists.newArrayList(getHamill(), getFord()));
    }

    public static Film getIndianaJonesFilm() {
        return new Film(3,
                "Indiana Jones and the Temple of Doom",
                Genre.ADVENTURE,
                INDIANA_JONES_DESCRIPTION,
                getSpielberg(),
                Lists.newArrayList(getFord(), getCapshaw()));
    }

    public static List<Film> getAllFilms() {
        return Lists.newArrayList(getTerminatorFilm(), getStarWarsFilm(), getIndianaJonesFilm());
    }

    public static Film getUpdatedTerminatorFilm() {
        Film film = getTerminatorFilm();
        film.setName("Terrrrrrrrrminator");
        film.setGenre(Genre.FANTASY);
        film.setDirector(getArni());
        film.getActors().add(getCameron());
        return film;
    }

    public static Film getNewFilm() {
        return new Film(null,
                "new Film",
                Genre.THRILLER,
                "Description for new film",
                getArni(),
                Lists.newArrayList(getHamill(), getCapshaw(), getHamilton()));
    }
}
