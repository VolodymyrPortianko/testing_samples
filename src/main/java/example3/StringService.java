package example3;

/**
 * Created on 18.11.2016.
 * Time 12:10
 *
 * @author Volodymyr Portianko
 */
public class StringService {

    public String getString() {
        return "string";
    }

    public String doubleString(String inputString) {
        return inputString + inputString;
    }

    public String multiplyString(String inputString, int multiplier) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < multiplier; i++) {
            builder.append(inputString);
        }

        return builder.toString();
    }
}
