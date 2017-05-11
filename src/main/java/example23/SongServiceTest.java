package example23;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
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
    public void testPlaySong() throws Exception {
        String anyName = "anyName";
        Song dummySong = new Song("any", "any", new byte[]{});
        Mockito.doReturn(dummySong).when(songStorageMock).getSongByName(anyName);

        sut.playSong(anyName);

        Mockito.verify(playerMock, Mockito.times(1)).turnOn();
        Mockito.verify(playerMock, Mockito.times(1)).play(dummySong);
        Mockito.verify(playerMock, Mockito.times(1)).turnOff();
    }




    @Test
    public void testPlaySongCorrect() throws Exception {
        String anyName = "anyName";
        Song dummySong = new Song("any", "any", new byte[]{});
        Mockito.doReturn(dummySong).when(songStorageMock).getSongByName(anyName);

        sut.playSong(anyName);

        InOrder inOrder = Mockito.inOrder(songStorageMock, playerMock);

        inOrder.verify(songStorageMock).getSongByName(anyName);
        inOrder.verify(playerMock).turnOn();
        inOrder.verify(playerMock).play(dummySong);
        inOrder.verify(playerMock).turnOff();
    }
}
