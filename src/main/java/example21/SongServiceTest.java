package example21;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
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

    private SongService sut;
    private SongStorage songStorageMock;
    private Player playerMock;

    @Before
    public void setUp() throws Exception {
        songStorageMock = Mockito.mock(SongStorage.class);
        playerMock = Mockito.mock(Player.class);
        sut = new SongService(songStorageMock, playerMock);
    }

    @Test
    public void testPlaySong() throws Exception {
        Song songA = new Song("anySinger", "A", new byte[]{});
        Song songB = new Song("anySinger", "B", new byte[]{});
        Song songC = new Song("anySinger", "C", new byte[]{});
        List<Song> songList = ImmutableList.of(songA, songB, songC);
        String anyName = "anyName";
        Song dummySong = new Song("any", "any", new byte[]{});
        Mockito.doReturn(dummySong).when(songStorageMock).getSongByName(anyName);

        sut.playSong(anyName);

        Mockito.verify(playerMock).play(Mockito.argThat(new ArgumentMatcher<Song>() {
            @Override
            public boolean matches(Object argument) {
                return songList.contains(argument);
            }
        }));
    }
}
