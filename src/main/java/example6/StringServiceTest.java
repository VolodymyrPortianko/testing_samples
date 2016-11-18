package example6;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

/**
 * Created on 18.11.2016.
 * Time 12:11
 *
 * @author Volodymyr Portianko
 */
@RunWith(JUnitParamsRunner.class)
public class StringServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test()
    @Parameters({"-100000", "-100", "-1", "0", "101", "1000000"})
    public void testMultiplyStringNotValidArgs(int multiplier) throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Multiplier should be in range from 1 to 100");

        sut.multiplyString("anyString", multiplier);
    }

}
