package example5;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created on 18.11.2016.
 * Time 12:11
 *
 * @author Volodymyr Portianko
 */
@RunWith(JUnitParamsRunner.class)
public class StringServiceTest {

    private StringService sut = new StringService();

    @Test
    @Parameters({"1", "10", "99", "100"})
    public void testMultiplyStringValidArgs(int multiplier) throws Exception {
        String anyString = "anyString";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < multiplier; i++) {
            builder.append(anyString);
        }
        String expected = builder.toString();

        String actual = sut.multiplyString(anyString, multiplier);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters({"-100000", "-100", "-1", "0", "101", "1000000"})
    public void testMultiplyStringNotValidArgs(int multiplier) throws Exception {
        sut.multiplyString("anyString", multiplier);
    }

}
