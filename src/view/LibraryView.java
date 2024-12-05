package view;

import javax.swing.*;
import controller.SearchController;
import controller.AddMusic;
import controller.PlaybackController;
import model.Song;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibraryView {
    private SearchController searchController;
    private AddMusic addMusic;
    private PlaybackController playbackController;
    private JTextField searchField;
    private JList<String> resultList;
    private JSlider durationSlider;
    private JButton playButton;
    private JButton pauseButton;
    private JButton skipButton;
    private JButton backButton;

    public LibraryView(SearchController searchController, AddMusic addMusic, PlaybackController playbackController) {
        this.searchController = searchController;
        this.addMusic = addMusic;
        this.playbackController = playbackController;
        
        // Set up the time update callback
        playbackController.setTimeUpdateCallback(progress -> {
            if (durationSlider != null && !durationSlider.getValueIsAdjusting()) {
                durationSlider.setValue((int) Math.round(progress));
            }
        });
        
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Music Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Increased size for better visibility
    
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add Music");
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        skipButton = new JButton("Skip");
        backButton = new JButton("Back");
        resultList = new JList<>();
        
        // Create and customize the duration slider
        durationSlider = new JSlider(0, 100, 0);
        durationSlider.setPreferredSize(new Dimension(300, 20));
        durationSlider.setBackground(Color.BLACK); // Set the background to black
        durationSlider.setForeground(Color.LIGHT_GRAY); // Set the slider color
    
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filter = searchField.getText();
                List<Song> results = searchController.filterSongs(filter);
                updateResultList(results);
            }
        });
    
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = JOptionPane.showInputDialog("Enter music file path:");
                addMusic.addFile(filePath);
            }
        });
    
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = resultList.getSelectedIndex();
                if (selectedIndex >= 0 && currentSongs != null) {
                    Song songToPlay = currentSongs.get(selectedIndex);
                    playbackController.play(songToPlay);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a song to play.");
                }
            }
        });
    
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playbackController.pause();
            }
        });
    
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playbackController.skip();
            }
        });
    
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playbackController.back();
            }
        });
    
        // Add change listener for the duration slider
        durationSlider.addChangeListener(e -> {
            if (!durationSlider.getValueIsAdjusting() && playbackController.getCurrentSong() != null) {
                double duration = playbackController.getDuration();
                if (duration > 0) {
                    double newTime = (durationSlider.getValue() / 100.0) * duration;
                    playbackController.seek(newTime);
                }
            }
        });
    
        // Main container panel
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
    
        // Top panel for search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
    
        // Center panel for song list
        mainPanel.add(new JScrollPane(resultList), BorderLayout.CENTER);
    
        // Bottom panel for playback controls
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        
        // Control panel with play/pause, back, skip buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(backButton);
        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(skipButton);
        bottomPanel.add(controlPanel, BorderLayout.NORTH);
        
        // Slider panel
        JPanel sliderPanel = new JPanel(new BorderLayout(5, 5));
        sliderPanel.add(durationSlider, BorderLayout.CENTER);
        bottomPanel.add(sliderPanel, BorderLayout.SOUTH);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private List<Song> currentSongs;  // Add this field to store the current list of songs

    private void updateResultList(List<Song> results) {
        this.currentSongs = results;  // Store the current list of songs
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Song song : results) {
            listModel.addElement(song.getDetails());
        }
        resultList.setModel(listModel);
    }

    public void displayFilteredResults() {
        // Logic to display filtered results in the UI
    }
}
