
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Playlist extends Entity {
    ArrayList<Song> songs;

    public Playlist() {
        super();
        this.songs = new ArrayList<Song>();
    }

    public Playlist(String n) {
        super(n);
        this.songs = new ArrayList<Song>();
    }

    /* add setters and getters here */
    public void setSongs(ArrayList<Song> setsongs) {
        songs = setsongs;
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /* add a new song.  */
    public void add(Song newSong) {
        int i = 0;
        for (Song song: songs) {
            if (song.equals(newSong)) {
                i = 1;
                System.out.println("Error. You already have this song in this playlist.");
                break;
            } else {

            }
        }
        if (i == 0) {
            songs.add(newSong);
        } else {

        }
    }

    /* remove Song s from the playlist */
    public void remove(Song s) {
        songs.remove(s);
    }

    /* shuffle - randomly reorder the playlist in place. */
    public void shuffle() {
        Collections.shuffle(this.songs);
    }
}
