package example100.filmlibrary.util.exeption;

/**
 * Created on 17.11.2016.
 * Time 20:39.
 *
 * @author Volodymyr Portianko
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
