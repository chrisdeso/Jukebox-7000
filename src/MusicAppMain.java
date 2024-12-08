import view.LibraryView;
import controller.SongDatabase;
import controller.SearchController;
import controller.AddMusic;
import controller.PlaybackController;
import controller.AccountDatabase;
import controller.LoginController;

import javafx.application.Platform;

public class MusicAppMain {
    public static void main(String[] args) {
        // Initialize JavaFX Platform
        Platform.startup(() -> {});
        
        // Initialize account management
        AccountDatabase accountDatabase = new AccountDatabase();
        LoginController loginController = new LoginController(accountDatabase);
        
        // Initialize song management with account support
        SongDatabase songDatabase = new SongDatabase(accountDatabase);
        SearchController searchController = new SearchController(songDatabase, accountDatabase);
        AddMusic addMusic = new AddMusic(songDatabase, accountDatabase);
        PlaybackController playbackController = new PlaybackController();
        
        // Create the main view with login support
        LibraryView libraryView = new LibraryView(searchController, addMusic, playbackController, loginController);
    }
}
