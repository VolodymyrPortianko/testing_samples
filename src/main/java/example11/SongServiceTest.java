package example11;

import com.google.common.collect.ImmutableList;
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
    public void testGetSortedSongs() throws Exception {
        Song song1 = new Song("anySinger", "A", new byte[]{});
        Song song2 = new Song("anySinger", "B", new byte[]{});
        Song song3 = new Song("anySinger", "C", new byte[]{});

        SongStorage storageDummy = Mockito.mock(SongStorage.class);
        SongService sut = Mockito.spy(new SongService(storageDummy));
        Mockito.when(sut.getSongs()).thenReturn(ImmutableList.of(song2, song3, song1));

        List<Song> expected = ImmutableList.of(song1, song2, song3);
        List<Song> actual = sut.getSortedSongs();

        Assert.assertEquals(expected, actual);
    }
}
