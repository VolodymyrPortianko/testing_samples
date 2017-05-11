package example11;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Song> songs = songStorage.getAllSongs();
        System.out.println("Songs from storage was loaded. Count: " + songs.size());
        return songs;
    }

    public Song getSong(String name) {
        Song song = songStorage.getSongByName(name);
        System.out.println(String.format("Song %s was loaded", song.getName()));
        return song;
    }

    public List<Song> getSongsByNames(String... names) {
        return Arrays.stream(names).map(this::getSong).collect(Collectors.toList());
    }
}
