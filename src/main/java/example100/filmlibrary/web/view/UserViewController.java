package example100.filmlibrary.web.view;

import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.UserService;
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
@RequestMapping("/users")
public class UserViewController {

    public static final String USER_VIEW = "user_page.jsp";

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public String userPage(@PathVariable Integer userId, Model model) {
        model.addAttribute(userService.get(userId));
        return USER_VIEW;
    }

    @PostMapping(value = "/{userId}")
    public String updateProfile(@Valid @ModelAttribute User user,
                                BindingResult result,
                                @PathVariable Integer userId,
                                ModelMap model) {
        if (result.hasErrors()) {
            return USER_VIEW;
        } else {
            user.setId(userId);
            userService.update(user);
            return USER_VIEW;
        }
    }

    @GetMapping(value = "new")
    public String newUserForm(Model model) {
        model.addAttribute(new User());
        return USER_VIEW;
    }

    @PostMapping(value = "new")
    public String createNewUser(@Valid @ModelAttribute User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return USER_VIEW;
        } else {
            userService.save(user);
            return "redirect:/users/" + user.getId();
        }
    }
}
