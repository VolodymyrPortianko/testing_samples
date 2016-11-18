package example10;

/**
 * Created on 18.11.2016.
 * Time 13:36
 *
 * @author Volodymyr Portianko
 */
public class Song {
    private String singer;
    private String name;
    private byte[] rawMp3;

    public Song(String singer, String name, byte[] rawMp3) {
        this.singer = singer;
        this.name = name;
        this.rawMp3 = rawMp3;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getRawMp3() {
        return rawMp3;
    }

    public void setRawMp3(byte[] rawMp3) {
        this.rawMp3 = rawMp3;
    }
}
