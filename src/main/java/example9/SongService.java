package example9;

import java.util.List;

/**
 * Created on 18.11.2016.
 * Time 13:45
 *
 * @author Volodymyr Portianko
 */
public class SongService {

    private SongStorage songStorage;

    public SongService(SongStorage songStorage) {
        this.songStorage = songStorage;
    }

    public List<Song> getSongs() {
        return songStorage.getAllSongs();
    }
}
