/* This class represents a Song */

public class Song extends Entity {
    String filename;
    Artist artist;
    int timesPlayed;
    String runningTime;
    Album album;

    /* you complete this */
    public Song() {
        super("");
        filename = "";
        artist = new Artist();
        timesPlayed = 0;
        runningTime = "";
    }

    /* you complete this */
    public Song(String title, String filename1, Artist artist1, int timesPlayed1, String runningTime1) {
        super(title);
        filename = filename1;
        artist = artist1;
        timesPlayed = timesPlayed1;
        runningTime = runningTime1;
    }

    /* add setters and getters */
    public void setAlbum(Album setalbum) {
        album = setalbum;
    }
    public Album getAlbum() {
        return album;
    }

    public void setFilename(String setfilename) {
        filename = setfilename;
    }
    public String getFilename() {
        return filename;
    }

    public void setArtist(Artist setartist) {
        artist = setartist;
    }
    public Artist getArtist() {
        return artist;
    }

    public void setTimesPlayed(int settimesplayed) {
        timesPlayed = settimesplayed;
    }
    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setRunningTime(String setrunningtime) {
        runningTime = setrunningtime;
    }
    public String getRunningTime() {
        return runningTime;
    }

    /* you complete this */
    public String toString() {
        return "Song name: " + this.name +"\nFilename: " + this.filename + "\nDate created: " + this.dateCreated + "\nArtist: " + this.artist.name + "\nTimes played: " + this.timesPlayed + "\nRunning time: " + this.runningTime;
    }

    /* you complete this. Assume that two songs are equal if they have the same name and the same artist. */
    public boolean equals(Song equalsong) {
        if (this.name == equalsong.name && this.artist.name == equalsong.artist.name) {
            return true;
        } else {
            return false;
        }
    }

}
