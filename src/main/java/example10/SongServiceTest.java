package example10;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created on 18.11.2016.
 * Time 13:51
 *
 * @author Volodymyr Portianko
 */
public class SongServiceTest {

    @Test
    public void testGetSong() throws Exception {
       SongStorage songStorageMock = Mockito.mock(SongStorage.class);
       SongService sut = new SongService(songStorageMock);
       String testName = "demoName";
       Song song = Mockito.mock(Song.class);
       Mockito.when(songStorageMock.getSongByName(testName)).thenReturn(song);
       Assert.assertEquals(sut.getSong(testName), song);


    }
}
