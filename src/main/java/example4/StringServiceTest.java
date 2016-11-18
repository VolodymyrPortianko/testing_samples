package example4;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 18.11.2016.
 * Time 12:11
 *
 * @author Volodymyr Portianko
 */
public class StringServiceTest {

    private StringService sut = new StringService();

    @Test
    public void testMultiplyString() throws Exception {
        String anyString = "anyString";
        String expected = anyString + anyString + anyString;

        String actual = sut.multiplyString(anyString, 3);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyStringIntArgBelowZero() throws Exception {
        sut.multiplyString("anyString", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyStringIntArgGreaterThan100() throws Exception {
        sut.multiplyString("anyString", 101);
    }

}
