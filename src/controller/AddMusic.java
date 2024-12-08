package controller;

import model.Song;
import model.Account;

import java.io.File;

public class AddMusic {
    private SongDatabase songDatabase;
    private AccountDatabase accountDatabase;

    public AddMusic(SongDatabase songDatabase, AccountDatabase accountDatabase) {
        this.songDatabase = songDatabase;
        this.accountDatabase = accountDatabase;
    }

    public void addFile(String filePath) {
        Account currentUser = accountDatabase.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("No user logged in.");
        }

        File file = new File(filePath);
        if (file.exists() && (filePath.endsWith(".mp3") || filePath.endsWith(".wav"))) {
            Song newSong = new Song(file.getName(), "Unknown Artist", filePath);
            songDatabase.addSong(newSong);
            System.out.println("Added: " + newSong.getDetails() + " to " + currentUser.getUsername() + "'s library");
        } else {
            throw new IllegalArgumentException("Invalid music file.");
        }
    }
}
