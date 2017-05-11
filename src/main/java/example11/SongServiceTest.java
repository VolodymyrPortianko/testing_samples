package example11;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
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
        Song songA = new Song("anySinger", "A", new byte[]{});
        Song songB = new Song("anySinger", "B", new byte[]{});
        Song songC = new Song("anySinger", "C", new byte[]{});

        SongStorage storageDummy = Mockito.mock(SongStorage.class);
        SongService sut = Mockito.spy(new SongService(storageDummy));
        Mockito.when(sut.getSong("A")).thenReturn(songA);
        Mockito.when(sut.getSong("B")).thenReturn(songB);
        Mockito.when(sut.getSong("C")).thenReturn(songC);

        List<Song> expected = ImmutableList.of(songA, songB, songC);
        List<Song> actual = sut.getSongsByNames("A", "B", "C");

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testGetSortedSongsCorrect() throws Exception {
        Song songA = new Song("anySinger", "A", new byte[]{});
        Song songB = new Song("anySinger", "B", new byte[]{});
        Song songC = new Song("anySinger", "C", new byte[]{});

        SongStorage storageDummy = Mockito.mock(SongStorage.class);
        SongService sut = Mockito.spy(new SongService(storageDummy));
//        Mockito.when(sut.getSong("A")).thenReturn(songA);
//        Mockito.when(sut.getSong("B")).thenReturn(songB);
//        Mockito.when(sut.getSong("C")).thenReturn(songC);
        Mockito.doReturn(songA).when(sut).getSong("A");
        Mockito.doReturn(songB).when(sut).getSong("B");
        Mockito.doReturn(songC).when(sut).getSong("C");

        List<Song> expected = ImmutableList.of(songA, songB, songC);
        List<Song> actual = sut.getSongsByNames("A", "B", "C");

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testGetSortedSongsShort() throws Exception {
        Song songA = new Song("anySinger", "A", new byte[]{});
        Song songB = new Song("anySinger", "B", new byte[]{});
        Song songC = new Song("anySinger", "C", new byte[]{});

        SongStorage storageDummy = Mockito.mock(SongStorage.class);
        SongService sut = Mockito.spy(new SongService(storageDummy));
//        Mockito.doReturn(songA).doReturn(songB).doReturn(songC).when(sut).getSong(Mockito.anyString());
        Mockito.doReturn(songA).doReturn(songB).doReturn(songC)
                .when(sut).getSong(Mockito.argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object argument) {
                return false;
            }
        }));

        List<Song> expected = ImmutableList.of(songA, songB, songC);
        List<Song> actual = sut.getSongsByNames("A", "B", "C");

        Assert.assertEquals(expected, actual);
    }

}
