package example6;

/**
 * Created on 18.11.2016.
 * Time 12:10
 *
 * @author Volodymyr Portianko
 */
public class StringService {

    public String getString() {
        return "ABC";
    }

    public String doubleString(String inputString) {
        return inputString + inputString;
    }

    public String multiplyString(String inputString, int multiplier) {
        if (multiplier <= 0 || multiplier > 100) {
            throw new IllegalArgumentException("Multiplier should be in range from 1 to 100");
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < multiplier; i++) {
            builder.append(inputString);
        }

        return builder.toString();
    }
}
