package h11;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Represents an artist with a name and a list of albums.
 *
 * @param name   the name of the artist
 * @param albums the list of albums released by the artist
 */
public record Artist(String name, List<Album> albums) {
    /**
     * Retrieves all the songs from the artist's albums.
     *
     * @return a list of all songs from the artist's albums
     */
    @StudentImplementationRequired
    public List<Song> getAllSongs() {
        // TODO H11.2.1
        Stream<Song> songs = albums.stream().flatMap(album -> album.songs().stream());

        List<Song> songsList = songs.collect(Collectors.toList());

        return songsList;
    }

    /**
     * Retrieves all unique genres from the artist's albums.
     *
     * @return a list of all unique genres from the artist's albums
     */
    @StudentImplementationRequired
    public List<Genre> getAllGenres() {
        // TODO H11.2.2
        Stream<Genre> genres = albums.stream().map(Album::genre).distinct();
        List<Genre> genresList = genres.collect(Collectors.toList());

        return genresList;
    }
}
