import java.util.ArrayList;

public class Artist extends Entity {
    ArrayList<Song> songs;
    ArrayList<Album> albums;

    /* you complete this */
    public Artist() {
        super("");
        songs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
    }

    /* you complete this */
    public Artist(String name) {
        super(name);
        songs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
    }

    /* add setters and getters */
    public void setSongs(ArrayList<Song> setsongs){
        songs = setsongs;
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setAlbums(ArrayList<Album> setalbums) {
        albums = setalbums;
    }
    public ArrayList<Album> getAlbums() {
        return albums;
    }
    /* you complete this */

    /* you complete this */
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("Artist name: " + this.name + "\nSongs of this Artist: ");

        if (songs.size() == 0) {

        } else {
            for (Song song: songs) {
                str.append(song.getName());
                str.append(", ");
            }
        }
        str.append("\nAlbums of this artist: ");
        if (albums.size() == 0) {

        } else {
            for (Album album: albums) {
                str.append(album.getName());
                str.append(", ");
            }
        }
        return str.toString();
    }

    /* you complete this. Assume that two artists are equal if they have the same name. */
    public boolean equals(Artist equalartist) {
        if (equalartist.name == this.name) {
            return true;
        } else {
            return false;
        }
    }
}
