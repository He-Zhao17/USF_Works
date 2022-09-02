public class LibraryTest {
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
        s1.setAlbum(al1);
        s2.setArtist(a1);
        s2.setAlbum(al1);
        s3.setArtist(a1);
        s3.setAlbum(al1);

        a1.albums.add(al1);


        al1.setTotalLength(String.valueOf(al1.songs.size()));


        Library lib = new Library();
        // only add albums is ok.
        lib.add(s1);
        lib.add(s2);
        lib.add(s3);
        lib.add(a1);
        lib.add(al1);


        //test findArtist
        System.out.println("----------------------");
        System.out.println("find artist: The Beatles\n");
        System.out.println(lib.findArtist("The Beatles"));
        System.out.println("\nfind artist: Jackon");
        System.out.println(lib.findArtist("Jackson"));

        //test findAlbum
        System.out.println("----------------------");
        System.out.println("\nfind album: Revolver");
        System.out.println(lib.findAlbum("Revolver"));
        System.out.println("\nfind album: Whatever");
        System.out.println(lib.findAlbum("Whatever"));

        //test findSong
        System.out.println("----------------------");
        System.out.println("\nfind song: Here Comes The Sun");
        System.out.println(lib.findSong("Here Comes The Sun", a1));
        System.out.println("\nfind the song: Whatever");
        System.out.println(lib.findSong("Whatever", a1));

        //test display
        System.out.println("----------------------");
        lib.display();

        //test delete
        System.out.println("----------------------");
        lib.delete(al1);
        System.out.println("\nfind album: Revolver");
        System.out.println(lib.findAlbum("Revolver"));

        //test display
        System.out.println("----------------------");
        lib.display();

        //test read
        System.out.println("----------------------");
        Library lib1 = new Library();
        lib1.readFromFile("./src/readfromfile.txt");
        lib1.display();

        //test write
        lib1.writeToFile("./src/output.txt");







    }

}
