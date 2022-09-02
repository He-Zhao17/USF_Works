public class BasicClassesTest {
    public static void main(String[] args) {
        // test Entity
        Entity entitytest1 = new Entity();
        entitytest1.setName("Pekko");
        System.out.println(entitytest1 + "\n");

        //test Song, Artist, Album.
        Song s1 = new Song();
        Song s2 = new Song();
        Song s3 = new Song();
        Artist a1 = new Artist();
        Album al1 = new Album();
        Album al2 = new Album();

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
        a1.albums.add(al2);

        al1.setTotalLength(String.valueOf(al1.songs.size()));
        al2.setTotalLength(String.valueOf(al2.songs.size()));

        System.out.println(s1 + "\n");
        System.out.println(s2 + "\n");
        System.out.println(s3 + "\n");
        System.out.println(a1 + "\n");
        System.out.println(al1 + "\n");
        System.out.println(al2 + "\n");

        // equal test
        Artist a1s = new Artist("The Beatles");
        Song s1s = new Song();
        s1s.setName("Here Comes The Sun");
        s1s.setArtist(a1s);
        Album al1s = new Album();
        al1s.setName("Revolver");
        al1s.setArtist(a1s);

        System.out.println(s1.equals(s1s));
        System.out.println(a1.equals(a1s));
        System.out.println(al1.equals(al1s));


    }
}
