package example100.filmlibrary.web.view;

import example100.filmlibrary.entity.Film;
import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by VP on 20.05.2017.
 */
@Controller
@RequestMapping("/films")
public class FilmViewController {

    public static final String FILM_VIEW = "film_page.jsp";

    @Autowired
    private FilmService filmService;

    @GetMapping("/{filmId}")
    public String filmPage(@PathVariable Integer filmId, Model model) {
        model.addAttribute(filmService.get(filmId));
        return FILM_VIEW;
    }

    @PostMapping(value = "/{filmId}")
    public String updateFilm(@Valid @ModelAttribute Film film,
                             BindingResult result,
                             @PathVariable Integer filmId,
                             ModelMap model) {
        if (result.hasErrors()) {
            return FILM_VIEW;
        } else {
            film.setId(filmId);
            filmService.update(film);
            return FILM_VIEW;
        }
    }

    @GetMapping(value = "new")
    public String newFilmForm(Model model) {
        model.addAttribute(new Film());
        return FILM_VIEW;
    }

    @PostMapping(value = "new")
    public String createNewUser(@Valid @ModelAttribute Film film, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return FILM_VIEW;
        } else {
            filmService.save(film);
            return "redirect:/films/" + film.getId();
        }
    }
}
