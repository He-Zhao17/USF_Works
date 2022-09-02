import java.io.*;
import java.util.*;

public class Library {
    ArrayList<Song> songs;
    ArrayList<Album> albums;
    ArrayList<Artist> artists;

    public Library() {
        songs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
        artists = new ArrayList<Artist>();
    }

    /* add setters and getters */
    public void setSongs(ArrayList<Song> inputsongs) {
        songs = inputsongs;
    }
    public ArrayList getSongs() {
        return songs;
    }

    public void setAlbums(ArrayList<Album> inputalbums) {
        albums = inputalbums;
    }
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setArtists(ArrayList<Artist> inputartists) {
        artists = inputartists;
    }
    public ArrayList<Artist> getArtists() {
        return artists;
    }

   /* you complete this. Return an empty Album if the search fails. If there is more than one match, return the first */
    public Album findAlbum(String name) {
        for (Album album: albums) {
            if (album.getName().equals(name)) {
                return album;
            } else {

            }
        }
        Album emptyalbum = new Album();
        return emptyalbum;
    }

    /* you complete this. Return an empty Artist if the search fails. If there is more than one match, return the first*/
    public Artist findArtist(String name) {
        for (Artist artist: artists) {
            if (artist.getName().equals(name)) {
                return artist;
            } else {

            }
        }
        Artist emptyartist = new Artist();
        return emptyartist;
    }

    /* you complete this. Return an empty Song if the search fails. If there is more than one match, return the first*/
    public Song findSong(String name, Artist a) {
        for (Song song: songs) {
            if (song.getName().equals(name) && song.getArtist().equals(a)) {
                return song;
            }
        }
        Song emptysong = new Song();
        return emptysong;
    }

    /* you complete this. */


    public void add(Entity e) {
      if (e instanceof Song) {
          songs.add((Song) e);
      } else if (e instanceof Artist) {
          artists.add((Artist) e);
      } else if (e instanceof Album) {
          albums.add((Album) e);
      } else {
          System.out.println("Error. Incorrect type.");
      }
    }
    /* you complete this */
    // Will all the songs and albums of an artist be deleted when this artist has been deleted?
    // If this song is also in a playlist. Should I delete that song from the playlist? Or make some notification?
    public void delete(Entity e) {
        if (e instanceof Song) {
            songs.remove((Song)e);
            for (Artist artist: artists) {
                if (artist.equals(((Song) e).artist)) {
                    for (Album album: artist.albums) {
                        for (Song song: album.songs) {
                            if (song.equals(e)) {
                                album.songs.remove(e);
                            } else {

                            }
                        }
                    }
                    for (Song song: artist.songs) {
                        if (song.equals(e)) {
                            artist.songs.remove(e);
                        } else {

                        }
                    }
                } else {

                }
            }
            for (Album album: albums) {
                if (album.artist.getName() == ((Song) e).artist.getName()) {
                    for (Song song: album.songs) {
                        if (song.equals(e)) {
                            album.songs.remove(e);
                        } else {

                        }
                    }
                } else {

                }
            }
        } else if (e instanceof Album) {
            albums.remove((Album)e);
            outer1:
            for (Artist artist: artists) {
                if (artist.equals(((Album) e).artist)) {
                    for (Album album: artist.albums) {
                        if (album.equals(e)) {
                            artist.albums.remove(e);
                            break outer1;
                        }
                    }
                }
            }
            for (Song song: ((Album) e).songs) {
                for (Song song1: songs) {
                    if (song1.equals(song)) {
                        songs.remove(song1);
                        break;
                    } else {

                    }
                }
                outer2:
                for (Artist artist: artists) {
                    if (artist.equals(((Album) e).artist)) {
                        for (Song song2: artist.songs) {
                            if (song2.equals(song)) {
                                artist.songs.remove(song2);
                                break outer2;
                            }
                        }
                    }
                }
            }
        } else if (e instanceof Artist) {
            artists.remove((Artist) e);
            for (Song song: ((Artist) e).songs) {
                for (Song song1: songs) {
                    if (song1.equals(song)) {
                        songs.remove(song1);
                        break;
                    }
                }
            }
            for (Album album: albums) {
                if (album.equals(e)) {
                    albums.remove(e);
                    break;
                }
            }
        } else {
            System.out.println("Fail to delete from library. This is an incorrect type.");
        }
    }

    /* you complete this. Print out the library in a pretty, user-friendly way. */
    public int compareoverride(Entity o1, Entity o2) {
        char[] lettermap = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char o1n = o1.name.charAt(0);
        char o2n = o2.name.charAt(0);
        int o1nl = 0;
        int o2nl = 0;
        // get o1 letter-key
        for (int i = 0; i < 26; i++) {
            if (lettermap[i] == o1n) {
                o1nl = i;
            } else {

            }
        }
        // get o2 letter-key
        for (int j = 0; j < 26; j++) {
            if (lettermap[j] == o2n) {
                o2nl = j;
            } else {

            }
        }
        return o1nl - o2nl;
    }

    public void display() {
        // songs
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return compareoverride(o1, o2);
            }
        });
        System.out.println("Your songs: \n");
        for (Song song: songs) {
            System.out.println(song.toString());
            System.out.println("\n\n");
        }
        System.out.println("\n");
        // artists
        Collections.sort(artists, new Comparator<Artist>() {
            @Override
            public int compare(Artist o1, Artist o2) {
                return compareoverride(o1, o2);
            }
        });
        System.out.println("Your artists: ");
        for (Artist artist: artists) {
            System.out.println(artist.toString());
            System.out.println("\n\n");
        }
        System.out.println("\n");
        // albums
        Collections.sort(albums, new Comparator<Album>() {
            @Override
            public int compare(Album o1, Album o2) {
                return compareoverride(o1, o2);
            }
        });
        System.out.println("Your albums: ");
        for (Album album: albums) {
            System.out.println(album.toString());
            System.out.println("\n\n");
        }
        System.out.println("\n");
    }

    /* you complete this. Return the first match, using the equals() method to determine if something is a duplicate.
        Return an empty Entity if no match is found.
    */
    public Entity findDuplicate(Entity e) {
        // Artist
        if (e instanceof Artist) {
            Artist ea = (Artist) e;
            for (int i = 0; i < artists.size(); i++) {
                if (artists.get(i).equals(ea)) {
                    return artists.get(i);
                } else {

                }
            }
            // Song.
        } else if (e instanceof Song) {
            Song es = (Song) e;
            for (Song eqsong : songs) {
                if (eqsong.equals(es)) {
                    return eqsong;
                } else {

                }
            }
            // Album.
        } else if (e instanceof Album) {
            Album eal = (Album) e;
            for (Album eqalbum : albums) {
                if (eqalbum.equals(eal)) {
                    return eqalbum;
                } else {

                }
            }
        }
        Entity empentity = new Entity();
        return empentity;
    }

    /* you complete this. Read from a file that has a CSV format like:
    Here Comes the Sun, The Beatles, Abbey Road, 3:22
    Tomorrow Never Knows, The Beatles, Revolver, 2:56

     */

    //In this section, I have read your code in your Github, I use your thought because yours is better than mine.
    public void readFromFile(String filenameofimport) {
        File imf;
        Scanner scan;
        String line;
        try {
            imf = new File(filenameofimport);
            scan = new Scanner(imf);
            while (scan.hasNext()) {
                line = scan.nextLine();
                String[] s = line.split(", ");
                Artist newartist = findArtist(s[1].strip());
                if (newartist.getName().length() == 0) {
                    newartist = new Artist(s[1].strip());
                    add(newartist);
                } else {

                }
                Album newalbum = findAlbum(s[2]);
                if (newalbum.getName().length() == 0) {
                    newalbum = new Album(s[2].strip());
                    newalbum.setArtist(newartist);
                    newartist.getAlbums().add(newalbum);
                    add(newalbum);
                } else {

                }

                Song newsong = new Song();
                newsong.setName(s[0].strip());
                newsong.setArtist(newartist);
                newsong.setAlbum(newalbum);
                newsong.setRunningTime(s[3].strip());
                newalbum.getSongs().add(newsong);
                newartist.songs.add(newsong);
                songs.add(newsong);
            }
        } catch (IOException e) {
            System.out.println("Errot. Cannot read this file.");
            return;
        }
    }

    /* write the data out to a file in the exact same format. */
    public void writeToFile(String fileout) {
        PrintWriter filewritten;
        try {
            filewritten = new PrintWriter(fileout);
            outer:
            for (Song song: songs) {
                // I do not know whether I should add "Album album" into class Song because there is not that instance variable in your code.
                // So I get that in a stupid way.
                for (Album album: albums) {
                    for (Song song1: album.songs) {
                        if (song1.getName() == song.getName() && song1.getArtist().equals(song.getArtist())) {
                            filewritten.println(song.getName() + ", " + song.getArtist().getName() + ", " + album.getName() + ", " + song.getRunningTime() + "\n");
                            continue outer;
                        } else {

                        }
                    }
                }
            }
            filewritten.close();
        } catch (IOException e) {
            System.out.println("Error! Cannot output the library.");
            e.printStackTrace();
            return;
        }
    }
}
