import view.LibraryView;
import controller.SongDatabase;
import controller.SearchController;
import controller.AddMusic;
import controller.PlaybackController;

import javafx.application.Platform;

public class MusicAppMain {
    public static void main(String[] args) {
        // Initialize JavaFX Platform
        Platform.startup(() -> {});
        SongDatabase songDatabase = new SongDatabase();
        SearchController searchController = new SearchController(songDatabase);
        AddMusic addMusic = new AddMusic(songDatabase);
        PlaybackController playbackController = new PlaybackController();
        
        LibraryView libraryView = new LibraryView(searchController, addMusic, playbackController);
        // Initialize the UI and show the main window
    }
}
