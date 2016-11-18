package example9;

import com.google.common.collect.Lists;
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
        Song dummySong = Mockito.mock(Song.class);
        SongStorage songStorageMock = Mockito.mock(SongStorage.class);
        Mockito.when(songStorageMock.getAllSongs()).thenReturn(Lists.newArrayList(dummySong));
        SongService sut = new SongService(songStorageMock);

        List<Song> actual = sut.getSongs();

        Assert.assertEquals(songStorageMock.getAllSongs(), actual);
    }
}
