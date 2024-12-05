public class Song {
    private String title;
    private String artist;
    private String filePath;
    private int duration; // in seconds
    private boolean isPlaying;
    
    // Constructor
    public Song(String title, String artist, String filePath, int duration) {
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
        this.duration = duration;
        this.isPlaying = false;
    }
    
    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    // Play functionality
    public void play() {
        isPlaying = true;
        System.out.println("Now playing: " + title + " by " + artist);
        // TODO: Add actual playback functionality when we add JavaFX
    }
    
    public void stop() {
        isPlaying = false;
        System.out.println("Stopped playing: " + title);
    }
    
    // Method to get song details as shown in sequence diagram
    public String getDetails() {
        return "Title: " + title + "\n" +
               "Artist: " + artist + "\n" +
               "Duration: " + formatDuration(duration);
    }
    
    // Helper method to format duration from seconds to mm:ss
    private String formatDuration(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
}