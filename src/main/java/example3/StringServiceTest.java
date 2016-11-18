package example3;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 18.11.2016.
 * Time 12:11
 *
 * @author Volodymyr Portianko
 */
public class StringServiceTest {

    @Test
    public void testMultiplyString() throws Exception {
        StringService sut = new StringService();
        String anyString = "anyString";
        String expected = anyString + anyString + anyString;

        String actual = sut.multiplyString(anyString, 3);

        Assert.assertEquals(expected, actual);
    }

}
