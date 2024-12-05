package controller;

import model.Song;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class SongDatabase {
    private List<Song> songs;

    public SongDatabase() {
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> querySongsByFilter(String filter) {
        return songs.stream()
                .filter(song -> song.getTitle().toLowerCase().contains(filter.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

    public Song findSongByTitleAndArtist(String title, String artist) {
        return songs.stream()
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
}
