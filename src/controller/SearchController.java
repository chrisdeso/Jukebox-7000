package controller;

import model.Song;
import model.Account;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SearchController {
    private SongDatabase songDatabase;
    private AccountDatabase accountDatabase;

    public SearchController(SongDatabase songDatabase, AccountDatabase accountDatabase) {
        this.songDatabase = songDatabase;
        this.accountDatabase = accountDatabase;
    }

    public List<Song> filterSongs(String filter) {
        Account currentUser = accountDatabase.getCurrentUser();
        if (currentUser == null) {
            return new ArrayList<>(); // Return empty list if no user is logged in
        }
        return songDatabase.querySongsByFilter(filter);
    }

    public Song getSongByDetails(String title, String artist) {
        Account currentUser = accountDatabase.getCurrentUser();
        if (currentUser == null) {
            return null; // Return null if no user is logged in
        }
        return songDatabase.findSongByTitleAndArtist(title, artist);
    }

    public List<Song> getCurrentUserSongs() {
        Account currentUser = accountDatabase.getCurrentUser();
        if (currentUser == null) {
            return new ArrayList<>();
        }
        return songDatabase.getCurrentUserSongs();
    }
}
