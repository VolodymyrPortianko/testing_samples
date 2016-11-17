package example100.filmlibrary.web.rest;

import example100.filmlibrary.entity.Film;
import example100.filmlibrary.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created on 18.11.2016.
 * Time 00:03.
 *
 * @author Volodymyr Portianko
 */
@RestController
@RequestMapping("/rest/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Film> get(@PathVariable int id) {
        return ResponseEntity.ok(filmService.get(id));
    }

    @GetMapping(value = "/by")
    public ResponseEntity<Film> getByName(@RequestParam String email) {
        return ResponseEntity.ok(filmService.getByName(email));
    }

    @GetMapping()
    public ResponseEntity<List<Film>> getAll() {
        return ResponseEntity.ok(filmService.getAll());
    }

    @PostMapping
    public ResponseEntity<Film> create(@RequestBody Film film) {
        Film savedFilm = filmService.save(film);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/rest/films/" + savedFilm.getId())
                .build()
                .toUri();
        return ResponseEntity.created(uri).body(savedFilm);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        filmService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public void update(@RequestBody Film film, @PathVariable int id) {
        film.setId(id);
        filmService.update(film);
    }

}
