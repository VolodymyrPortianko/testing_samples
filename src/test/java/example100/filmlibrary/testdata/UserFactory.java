package example100.filmlibrary.testdata;

import com.google.common.collect.Lists;
import example100.filmlibrary.entity.User;

import java.util.List;
import java.util.function.Function;

/**
 * Created on 17.11.2016.
 * Time 12:07.
 *
 * @author Volodymyr Portianko
 */
public class UserFactory {

    public static final ModelMatcher<User, String> USER_MATCHER = new ModelMatcher<>(User::toString);

    public static User getAdmin() {
        return new User(1, "admin", "admin@gmail.com", "111");
    }

    public static User getUser1() {
        return new User(2, "user1", "user1@gmail.com", "222");
    }

    public static User getUser2() {
        return new User(3, "user2", "user2@gmail.com", "333");
    }

    public static List<User> getAllUsers() {
        return Lists.newArrayList(getAdmin(), getUser1(), getUser2());
    }

    public static User getNewUser() {
        return new User(null, "newUser", "newUser@gmail.com", "444");
    }
}
