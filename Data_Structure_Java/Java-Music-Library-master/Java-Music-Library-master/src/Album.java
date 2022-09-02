import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class Album extends Entity {
    ArrayList<Song> songs;
    Artist artist;
    String totalLength;

    /* you complete this */
    public Album() {
        super("");
        songs = new ArrayList<Song>();
        artist = new Artist();
        totalLength = "";

    }

    /* you complete this */
    public Album(String n, ArrayList<Song> songs1, Artist artist1, String totalLength1) {
        super(n);
        songs = songs1;
        artist = artist1;
        totalLength = totalLength1;
    }

    /* you complete this */
    public Album(String n) {
        super(n);
        songs = new ArrayList<Song>();
        artist = new Artist();
        totalLength = String.valueOf(this.songs.size());
    }

    /* add setters and getters here */
    public void setSongs(ArrayList<Song> setsongs) {
        songs = setsongs;
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setArtist(Artist setartist) {
        artist = setartist;
    }
    public Artist getArtist() {
        return artist;
    }

    public void setTotalLength(String settotalLength) {
        totalLength = settotalLength;
    }
    public String getTotalLength() {
        return totalLength;
    }
    /* you complete this */
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("Album name: " + this.name + "\nArtist: " + this.artist.name + "\nThe songs of this album: ");
        if (this.songs.size() == 0) {

        } else {
            for (Song song: songs) {
                str.append(song.getName());
                str.append(", ");
            }
        }
        return str.toString();
    }



    /* you complete this. Assume that two albums are equal if they have the same name and the same artist. */
    public boolean equals(Album album_1) {
        if (this.name == album_1.name && this.artist.name == album_1.artist.name) {
            return true;
        } else {
            return false;
        }
    }
}
