package example7;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * Created on 18.11.2016.
 * Time 13:51
 *
 * @author Volodymyr Portianko
 */
public class SongServiceTest {

    @Test
    public void testGetSongs() throws Exception {
        SongStorage songStorage = new SongStorage();
        SongService sut = new SongService(songStorage);

        List<Song> actual = sut.getSongs();

        Assert.assertEquals(songStorage.getAllSongs(), actual);
    }
}
