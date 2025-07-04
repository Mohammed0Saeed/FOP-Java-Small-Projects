package h11;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Represents a user of the music streaming platform.
 */
public class User {
    private final String username;
    private double pricePerMonth;
    private List<PlayedSong> playingHistory;

    /**
     * Constructs a new User with the specified username, monthly price, and playing history.
     *
     * @param username       the username of the user
     * @param pricePerMonth  the monthly subscription price for the user
     * @param playingHistory the list of songs played by the user
     */
    @DoNotTouch
    public User(String username, double pricePerMonth, List<PlayedSong> playingHistory) {
        this.username = username;
        this.pricePerMonth = pricePerMonth;
        this.playingHistory = playingHistory;
    }

    @Override
    @DoNotTouch
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            ", pricePerMonth=" + pricePerMonth +
            ", playingHistory=" + playingHistory +
            '}';
    }

    @Override
    @DoNotTouch
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Double.compare(pricePerMonth, user.pricePerMonth) == 0 && Objects.equals(username, user.username) && Objects.equals(playingHistory, user.playingHistory);
    }

    @Override
    @DoNotTouch
    public int hashCode() {
        return Objects.hash(username, pricePerMonth, playingHistory);
    }

    /**
     * Retrieves the username of the user.
     *
     * @return the username of the user
     */
    @DoNotTouch
    public String getUsername() {
        return username;
    }
    /**
     * Retrieves the monthly subscription price for the user.
     *
     * @return the monthly subscription price for the user
     */
    @DoNotTouch
    public double getPricePerMonth() {
        return pricePerMonth;
    }
    /**
     * Retrieves the list of songs played by the user.
     *
     * @return the playing history of the user
     */
    @DoNotTouch
    public List<PlayedSong> getPlayingHistory() {
        return playingHistory;
    }

    /**
     * Sets the monthly subscription price for the user.
     *
     * @param pricePerMonth the new monthly subscription price for the user
     */
    @DoNotTouch
    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    /**
     * Retrieves the list of unique songs played by the user.
     *
     * @return a list of unique songs played by the user
     */
    @StudentImplementationRequired
    public List<Song> getPlayedSongs() {
        // TODO H11.3.1
        Stream<Song> songsPlayed = this.playingHistory.stream().map(playedSong -> playedSong.song()).distinct();
        return songsPlayed.collect(Collectors.toList());
    }

    /**
     * Retrieves the play counts for each song played by the user.
     *
     * @return a list of entries where each entry is a song and its play count, sorted by play count in descending order.
     * Songs with the same play count are ordered alphabetically by the song title.
     */
    @StudentImplementationRequired
    public List<Map.Entry<Song, Long>> getPlayCounts() {
        // TODO H11.3.2
        Map<Song, Long> playCounts = playingHistory.stream().collect(Collectors.groupingBy(PlayedSong::song, Collectors.counting()));

        List<Map.Entry<Song, Long>> playedSongsCount = playCounts.entrySet().stream().sorted((song1, song2) -> song2.getValue().compareTo(song1.getValue())).collect(Collectors.toList());

        return playedSongsCount;
    }

    /**
     * Retrieves the user's favorite song based on play count.
     *
     * @return the user's favorite song, or null if there are no played songs
     */
    @StudentImplementationRequired
    public Song getFavoriteSong() {
        List<Map.Entry<Song, Long>> mostPlayedSongs = getPlayCounts();

        return mostPlayedSongs.stream().max(Map.Entry.comparingByValue()).stream().findFirst().get().getKey();
    }

    /**
     * Retrieves a list of the top played songs of the user formatted as strings.
     *
     * @return a list of strings representing the top 3 most played songs of the user and their play counts in the format "[title] ([count] plays)"
     */
    @StudentImplementationRequired
    public List<String> getTopPlayedSongsList() {
        // TODO H11.3.4
        Stream <Map.Entry<Song, Long>> playList = getPlayCounts().stream().limit(3);

        List<String> songNames = playList.map(song -> song.getKey() + "(" + song.getValue() + " plays)").collect(Collectors.toList());

        return songNames;
    }
}
