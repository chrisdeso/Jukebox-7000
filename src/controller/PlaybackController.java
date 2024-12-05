package controller;

import model.Song;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import java.util.function.Consumer;

public class PlaybackController {
    private Song currentSong;
    private boolean isPlaying;
    private MediaPlayer mediaPlayer;
    private Timeline timeline;
    private Consumer<Double> timeUpdateCallback;

    public void setTimeUpdateCallback(Consumer<Double> callback) {
        this.timeUpdateCallback = callback;
    }

    public void play(Song song) {
        System.out.println("Attempting to play song: " + song.getDetails());
        if (song == null || song.getFilePath() == null || song.getFilePath().isEmpty()) {
            System.out.println("No valid song selected to play.");
            return;
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        this.currentSong = song;
        try {
            String uri = new java.io.File(song.getFilePath()).toURI().toString();
            Media media = new Media(uri);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(() -> {
                isPlaying = false;
                System.out.println("Playback finished.");
            });
            mediaPlayer.setVolume(1.0); // Set volume to maximum
            mediaPlayer.play();
            isPlaying = true;
            System.out.println("Playing: " + song.getDetails());
        } catch (Exception e) {
            System.out.println("Error playing song: " + e.getMessage());
            return;
        }
    }

    public void skip() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(10))); // Skip forward 10 seconds
        }
    }

    public void back() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(javafx.util.Duration.seconds(10))); // Go back 10 seconds
        }
    }

    public void pause() {
        if (isPlaying && mediaPlayer != null) {
            mediaPlayer.pause();
            isPlaying = false;
            System.out.println("Paused: " + currentSong.getDetails());
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            isPlaying = false;
            currentSong = null;
            System.out.println("Playback stopped.");
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public double getDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getTotalDuration().toSeconds();
        }
        return 0.0;
    }

    public void seek(double seconds) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(javafx.util.Duration.seconds(seconds));
        }
    }

    public double getCurrentTime() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentTime().toSeconds();
        }
        return 0.0;
    }
}
