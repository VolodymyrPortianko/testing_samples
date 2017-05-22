package example100.filmlibrary.web.rest;

import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created on 17.11.2016.
 * Time 17:55.
 *
 * @author Volodymyr Portianko
 */
@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @GetMapping(value = "/by")
    public ResponseEntity<User> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/rest/users/" + savedUser.getId())
                .build()
                .toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public void update(@RequestBody User user, @PathVariable int id) {
        user.setId(id);
        userService.update(user);
    }
}
