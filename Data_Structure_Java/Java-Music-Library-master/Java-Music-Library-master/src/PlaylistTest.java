public class PlaylistTest {
    public static void main(String args[]) {
        Song s1 = new Song();
        Song s2 = new Song();
        Song s3 = new Song();
        Artist a1 = new Artist();
        Album al1 = new Album();

        s1.setName("Here Comes The Sun");
        s2.setName("Tomorrow Never Knows");
        s3.setName("Something");
        s3.setFilename("Something");
        s1.setFilename("Here Comes The Sun.flac");
        s2.setFilename("Tomorrow Never Knows.flac");
        s3.setFilename("Something.flac");

        a1.setName("The Beatles");
        a1.songs.add(s1);
        a1.songs.add(s2);
        a1.songs.add(s3);

        al1.setName("Revolver");
        al1.songs.add(s1);
        al1.songs.add(s2);
        al1.songs.add(s3);
        al1.setArtist(a1);

        s1.setArtist(a1);
        s2.setArtist(a1);
        s3.setArtist(a1);

        a1.albums.add(al1);


        al1.setTotalLength(String.valueOf(al1.songs.size()));

        Playlist pl = new Playlist();
        pl.setName("Playlist 1");
        pl.songs.add(s1);
        pl.songs.add(s2);
        pl.songs.add(s3);
        System.out.println(pl.songs);
        pl.shuffle();
        System.out.println(pl.songs);
    }

}
