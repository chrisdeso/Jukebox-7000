package controller;

import model.Song;

import java.util.List;
import java.util.stream.Collectors;

public class SearchController {
    private SongDatabase songDatabase;

    public SearchController(SongDatabase songDatabase) {
        this.songDatabase = songDatabase;
    }

    public List<Song> filterSongs(String filter) {
        return songDatabase.querySongsByFilter(filter);
    }

    public Song getSongByDetails(String title, String artist) {
        return songDatabase.findSongByTitleAndArtist(title, artist);
    }
}
