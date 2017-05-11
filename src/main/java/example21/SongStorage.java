package example21;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 18.11.2016.
 * Time 13:39
 *
 * @author Volodymyr Portianko
 */
public class SongStorage {

    private List<Song> songs = new ArrayList<>();

    public SongStorage() {
        init();
    }

    private void init() {
        songs.add(new Song("Metallica", "The Unforgiven", new byte[1024]));
        songs.add(new Song("Queen", "Bohemian Rhapsody", new byte[1024]));
        songs.add(new Song("Red Hot Chili Peppers", "Californication", new byte[1024]));
        throw new RuntimeException();
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    public Song getSongByName(String name) {
        return songs.stream().filter(song -> song.getName().equals(name)).findFirst().get();
    }
}
