package example22;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void testPlayRandomSong() throws Exception {

    }
}
