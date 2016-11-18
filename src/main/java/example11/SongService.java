package example11;

import java.util.List;

/**
 * Created on 18.11.2016.
 * Time 13:45
 *
 * @author Volodymyr Portianko
 */
public class SongService {

    private SongStorage songStorage;
    private Player player;

    public SongService(SongStorage songStorage, Player player) {
        this.songStorage = songStorage;
        this.player = player;
    }

    public List<Song> getSongs() {
        return songStorage.getAllSongs();
    }

    public Song getSong(String name) {
        return songStorage.getSongByName(name);
    }

    public void playSong(String name) {
        Song song = songStorage.getSongByName(name);
        player.play(song);
    }
}
