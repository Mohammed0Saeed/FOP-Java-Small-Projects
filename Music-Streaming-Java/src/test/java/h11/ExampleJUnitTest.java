package h11;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An example JUnit test class.
 */
public class ExampleJUnitTest {

    // Date Base
    List<Song> nfSongs = Arrays.asList(
        new Song("The Search", 240),
        new Song("Let You Down", 215),
        new Song("If You Want Love", 220),
        new Song("Hope", 230),
        new Song("Motto", 210)
    );

    List<Song> eminemSongs = Arrays.asList(
        new Song("Lose Yourself", 326),
        new Song("Rap God", 360),
        new Song("Godzilla", 210),
        new Song("Mockingbird", 251),
        new Song("Not Afraid", 259)
    );

    List<Song> billieSongs = Arrays.asList(
        new Song("Bad Guy", 194),
        new Song("Lovely", 201),
        new Song("Ocean Eyes", 230),
        new Song("Happier Than Ever", 298),
        new Song("Everything I Wanted", 245)
    );

    List<Song> beethovenSongs = Arrays.asList(
        new Song("Symphony No.5", 540),
        new Song("Für Elise", 180),
        new Song("Moonlight Sonata", 900),
        new Song("Ode to Joy", 400),
        new Song("Piano Concerto No.5", 2100)
    );

    List<Song> taylorSongs = Arrays.asList(
        new Song("Love Story", 235),
        new Song("You Belong with Me", 231),
        new Song("Shake It Off", 242),
        new Song("Blank Space", 231),
        new Song("All Too Well", 600)
    );

    // Albums
    Album nfAlbum = new Album("NF Collection", Genre.RAP, nfSongs);
    Album eminemAlbum = new Album("Eminem Hits", Genre.RAP, eminemSongs);
    Album billieAlbum = new Album("Billie Essentials", Genre.POP, billieSongs);
    Album beethovenAlbum = new Album("Beethoven Classics", Genre.CLASSICAL, beethovenSongs);
    Album taylorAlbum = new Album("Taylor Swift Favorites", Genre.POP, taylorSongs);
    Album emptyAlbum = new Album("Empty Album", Genre.CLASSICAL, new ArrayList<>());

    // Artists
    List<Artist> artists = Arrays.asList(
        new Artist("NF", Arrays.asList(nfAlbum)),
        new Artist("Eminem", Arrays.asList(eminemAlbum)),
        new Artist("Billie Eilish", Arrays.asList(billieAlbum)),
        new Artist("Beethoven", Arrays.asList(beethovenAlbum)),
        new Artist("Taylor Swift", Arrays.asList(taylorAlbum))
    );

    // Users
    User mohammed = new User("Mohammed", 9.99, Arrays.asList(
        new PlayedSong(nfSongs.get(0), new Date()),
        new PlayedSong(eminemSongs.get(2), new Date()),
        new PlayedSong(billieSongs.get(4), new Date())
    ));

    User larisa = new User("Larisa", 9.99, Arrays.asList(
        new PlayedSong(taylorSongs.get(1), new Date()),
        new PlayedSong(beethovenSongs.get(3), new Date()),
        new PlayedSong(nfSongs.get(2), new Date())
    ));

    @Test
    public void testAlbum() {
        assertEquals(nfSongs, nfAlbum.songs());
        assertEquals(List.of(nfSongs.get(0), nfSongs.get(3)), nfAlbum.getSongsLongerThan(220));
        assertEquals(223d, nfAlbum.getAverageDuration());
        assertEquals(0d, emptyAlbum.getAverageDuration());
    }

    @Test
    public void testArtist() {
        assertEquals(eminemSongs, artists.get(1).getAllSongs());
        assertEquals(List.of(Genre.RAP), artists.get(1).getAllGenres());
    }

    @Test
    public void testSong() {
        assertTrue(billieSongs.get(0).isLongerThan(190));
        assertFalse(billieSongs.get(1).isLongerThan(250));
    }

    @Test
    public void testUser() {
        assertEquals(List.of(nfSongs.get(0), eminemSongs.get(2), billieSongs.get(4)), mohammed.getPlayedSongs());
    }
}
