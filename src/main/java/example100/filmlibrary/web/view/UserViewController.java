package example100.filmlibrary.web.view;

import example100.filmlibrary.entity.User;
import example100.filmlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * Created by VP on 20.05.2017.
 */
@Controller
@RequestMapping("/users")
public class UserViewController {

    public static final String USER_VIEW = "user_page";

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public String userList(@PathVariable Integer userId, Model model) {
        model.addAttribute(userService.get(userId));
        return USER_VIEW;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public String updateProfile(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return USER_VIEW;
        } else {
            userService.update(user);
            return USER_VIEW;
        }
    }
}
