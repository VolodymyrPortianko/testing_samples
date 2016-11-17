package example100.filmlibrary.web.rest;

import example100.filmlibrary.entity.FilmReview;
import example100.filmlibrary.service.FilmReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * Created on 18.11.2016.
 * Time 00:03.
 *
 * @author Volodymyr Portianko
 */
@RestController
@RequestMapping("/rest/reviews")
public class FilmReviewController {

    @Autowired
    private FilmReviewService filmReviewService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FilmReview> get(@PathVariable int id) {
        return ResponseEntity.ok(filmReviewService.get(id));
    }

    @GetMapping()
    public ResponseEntity<List<FilmReview>> getAll(@RequestParam(required = false) Integer user,
                                                      @RequestParam(required = false) Integer film) {
        if (Objects.nonNull(user)) return ResponseEntity.ok(filmReviewService.getByUser(user));
        if (Objects.nonNull(film)) return ResponseEntity.ok(filmReviewService.getByFilm(film));
        throw new IllegalArgumentException();
    }

    @PostMapping
    public ResponseEntity<FilmReview> create(@RequestBody FilmReview film) {
        FilmReview savedReview = filmReviewService.save(film);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/rest/reviews/" + savedReview.getId())
                .build()
                .toUri();
        return ResponseEntity.created(uri).body(savedReview);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        filmReviewService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public void update(@RequestBody FilmReview review, @PathVariable int id) {
        review.setId(id);
        filmReviewService.update(review);
    }
}
