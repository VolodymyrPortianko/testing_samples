package example1;

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
    public void testGetString() throws Exception {
        StringService sut = new StringService();
        String expected = "ABC";

        String actual = sut.getString();

        Assert.assertEquals(expected, actual);
    }
}
