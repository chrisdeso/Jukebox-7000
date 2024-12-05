package controller;

import model.Song;

import java.io.File;

public class AddMusic {
    private SongDatabase songDatabase;

    public AddMusic(SongDatabase songDatabase) {
        this.songDatabase = songDatabase;
    }

    public void addFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && (filePath.endsWith(".mp3") || filePath.endsWith(".wav"))) {
            Song newSong = new Song(file.getName(), "Unknown Artist", filePath);
            songDatabase.addSong(newSong);
            System.out.println("Added: " + newSong.getDetails() + " with file path: " + filePath);
        } else {
            throw new IllegalArgumentException("Invalid music file.");
        }
    }
}
