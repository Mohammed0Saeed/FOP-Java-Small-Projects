package h11;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Represents a music streaming service with a list of artists and a list of users.
 *
 * @param artists the list of artists
 * @param users   the list of users
 */
public record MusicStreaming(List<Artist> artists, List<User> users) {
    /**
     * Retrieves all the songs from all the artists on the platform.
     *
     * @return a list of all songs available on the platform
     */
    @StudentImplementationRequired
    public List<Song> getAllSongs() {
        List<Song> allSongs = artists.stream().map(artist -> artist.getAllSongs().stream().collect(Collectors.toList())).flatMap(List::stream).collect(Collectors.toList());
        return allSongs;
    }

    /**
     * Generates a stream of random songs from the platform.
     *
     * @return a stream of random songs
     */
    @StudentImplementationRequired
    public Stream<Song> generateRandomPlaylist() {
        // TODO H11.4.2
        return Stream.generate(() -> getRandomSong()).distinct().collect(Collectors.toList()).stream();
    }

    /**
     * Retrieves a random song from the platform's song list.
     *
     * @return a random song, or null if there are no songs available
     */
    @DoNotTouch
    public Song getRandomSong() {
        List<Song> allSongs = getAllSongs();
        if (allSongs.isEmpty()) {
            return null;
        }
        return allSongs.get((int) (Math.random() * allSongs.size()));
    }

    /**
     * Retrieves a list of songs that are longer than the specified duration.
     *
     * @param durationInSeconds the duration in seconds to compare the song lengths against
     * @return a list of songs that are longer than the specified duration
     */
    @StudentImplementationRequired
    public List<Song> getSongsLongerThan(int durationInSeconds) {
        // TODO H11.4.3
        return getAllSongs().stream().filter(song -> song.isLongerThan(durationInSeconds)).collect(Collectors.toList());
    }

    /**
     * Retrieves all unique genres available on the platform.
     *
     * @return a list of all unique genres available on the platform
     */
    @StudentImplementationRequired
    public List<Genre> getAllGenres() {
        // TODO H11.4.4
        return artists.stream().map(artist -> artist.getAllGenres().stream().collect(Collectors.toList())).flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * Groups all albums by their genre.
     *
     * @return a map where the key is the genre and the value is a list of albums of that genre
     */
    @StudentImplementationRequired
    public Map<Genre, List<Album>> getAlbumsByGenre() {
        // TODO H11.4.5
        // playingHistory.stream().collect(Collectors.groupingBy(PlayedSong::song, Collectors.counting()));
        Map<Genre, List<Album>> albumsByGenre = artists.stream().flatMap(artist -> artist.albums().stream()).collect(Collectors.groupingBy(Album::genre));
        return albumsByGenre;
    }

    /**
     * Calculates the global play counts for all songs by summing the play counts from all users.
     *
     * @return a list of entries where each entry is a song and its total play count, sorted by play count in descending order.
     * Songs with the same play count are ordered alphabetically by the song title.
     */
    @StudentImplementationRequired
    public List<Map.Entry<Song, Long>> getGlobalPlayCounts() {
        // TODO H11.4.6
        List<Map.Entry<Song, Long>> songs = users.stream().flatMap(user -> user.getPlayCounts().stream()).collect(Collectors.toList());

        return songs.stream()
            .sorted(
                (song1, song2) ->
                    (song2.getValue().compareTo(song1.getValue())) + (song1.getValue().equals(song2.getValue()) ? (song1.getKey().title().compareTo(song2.getKey().title())) : 0)
            ).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of the top played songs formatted as strings.
     *
     * @return a list of strings representing the top 5 most played songs and their play counts in the format "[title] ([count] plays)"
     */
    @StudentImplementationRequired
    public List<String> getTopPlayedSongsList() {
        // TODO H11.4.6
        return getGlobalPlayCounts().stream().map(song -> song.getKey().title() + " (" + song.getValue() + " plays)").collect(Collectors.toList());
    }

    /**
     * Retrieves the total playtime of an artist's songs based on global play counts.
     *
     * @param artist the artist whose playtime is to be calculated
     * @return the total playtime of the artist's songs in seconds
     */
    @StudentImplementationRequired
    public long getArtistPlaytime(Artist artist) {
        // TODO H11.4.7
        Stream<Map.Entry<Song, Long>> playtime = getGlobalPlayCounts().stream().filter(song -> artist.getAllSongs().contains(song.getKey()));

        return playtime.mapToLong(song -> song.getKey().durationInSeconds() * song.getValue()).sum();
    }

    /**
     * Retrieves the total playtimes for all artists.
     *
     * @return a map where the key is the artist and the value is their total playtime in seconds
     */
    @StudentImplementationRequired
    public Map<Artist, Long> getArtistPlaytimes() {
        // TODO H11.4.7
        return artists.stream().collect(Collectors.toMap(a -> a, a -> getArtistPlaytime(a)));
    }

    /**
     * Retrieves the most played artist based on the total playtime of their songs.
     *
     * @return the most played artist, or null if there are no artists
     */
    @StudentImplementationRequired
    public Artist getMostPlayedArtist() {
        // TODO H11.4.8
        return artists.isEmpty() ?
            null : getArtistPlaytimes().entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    /**
     * Searches for songs that match the given predicate.
     *
     * @param predicate the predicate to filter songs
     * @return a list of songs that match the predicate
     */
    @StudentImplementationRequired
    public List<Song> searchSongs(Predicate<? super Song> predicate) {
        // TODO H11.4.9
        return getAllSongs().stream().filter(song -> predicate.test(song)).collect(Collectors.toList());
    }

    /**
     * Adjusts the monthly subscription price for all users by a given percentage.
     *
     * @param percentage the percentage to adjust the price by (e.g., 0.10 for a 10% increase)
     */
    @StudentImplementationRequired
    public void adjustPrice(double percentage) {
        // TODO H11.4.10
        users.stream().forEach(user -> user.setPricePerMonth(user.getPricePerMonth() + user.getPricePerMonth() * percentage));
    }
}
