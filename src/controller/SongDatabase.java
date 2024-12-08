package controller;

import model.Song;
import model.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class SongDatabase {
    private Map<String, List<Song>> userSongs;
    private AccountDatabase accountDatabase;

    public SongDatabase(AccountDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
        userSongs = new HashMap<>();
        // Initialize empty song lists for each user
        userSongs.put("Khanh", new ArrayList<>());
        userSongs.put("Chris", new ArrayList<>());
        userSongs.put("Hilario", new ArrayList<>());
    }

    public void addSong(Song song) {
        Account currentUser = accountDatabase.getCurrentUser();
        if (currentUser != null) {
            userSongs.computeIfAbsent(currentUser.getUsername(), k -> new ArrayList<>()).add(song);
        }
    }

    public List<Song> querySongsByFilter(String filter) {
        Account currentUser = accountDatabase.getCurrentUser();
        if (currentUser == null) return new ArrayList<>();

        List<Song> userSongList = userSongs.get(currentUser.getUsername());
        return userSongList.stream()
                .filter(song -> song.getTitle().toLowerCase().contains(filter.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

    public Song findSongByTitleAndArtist(String title, String artist) {
        Account currentUser = accountDatabase.getCurrentUser();
        if (currentUser == null) return null;

        List<Song> userSongList = userSongs.get(currentUser.getUsername());
        return userSongList.stream()
                .filter(song -> song.getTitle().equals(title) && song.getArtist().equals(artist))
                .findFirst()
                .orElse(null);
    }

    public List<Song> searchMusic(String query) {
        List<Song> results = new ArrayList<>();
        try {
            String apiUrl = "https://musicbrainz.org/ws/2/recording?query=" + query + "&fmt=json";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray recordings = jsonResponse.getJSONArray("recordings");

                for (int i = 0; i < recordings.length(); i++) {
                    JSONObject recording = recordings.getJSONObject(i);
                    String title = recording.getString("title");
                    String artist = recording.getJSONArray("artist-credit").getJSONObject(0).getString("name");
                    String id = recording.getString("id");
                    results.add(new Song(title, artist, "https://musicbrainz.org/recording/" + id));
                }
            } else {
                System.out.println("Error: Unable to fetch data from MusicBrainz API.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Song> getCurrentUserSongs() {
        Account currentUser = accountDatabase.getCurrentUser();
        if (currentUser == null) return new ArrayList<>();
        return userSongs.getOrDefault(currentUser.getUsername(), new ArrayList<>());
    }
}
