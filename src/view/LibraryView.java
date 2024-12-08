package view;

import javax.swing.*;
import controller.SearchController;
import controller.AddMusic;
import controller.PlaybackController;
import controller.LoginController;
import model.Song;
import model.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibraryView {
    private SearchController searchController;
    private AddMusic addMusic;
    private PlaybackController playbackController;
    private LoginController loginController;
    private JTextField searchField;
    private JList<String> resultList;
    private JSlider durationSlider;
    private JButton playButton;
    private JButton pauseButton;
    private JButton skipButton;
    private JButton backButton;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JLabel userLabel;
    private JPanel contentPanel; // Make contentPanel a class-level variable
    private CardLayout cardLayout; // Make cardLayout a class-level variable

    public LibraryView(SearchController searchController, AddMusic addMusic, PlaybackController playbackController, LoginController loginController) {
        this.searchController = searchController;
        this.addMusic = addMusic;
        this.playbackController = playbackController;
        this.loginController = loginController;
        
        playbackController.setTimeUpdateCallback(progress -> {
            if (durationSlider != null && !durationSlider.getValueIsAdjusting()) {
                durationSlider.setValue((int) Math.round(progress));
            }
        });
        
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Music Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create card layout for switching between login and main panels
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Create login panel
        loginPanel = createLoginPanel();
        contentPanel.add(loginPanel, "LOGIN");

        // Create main panel
        mainPanel = createMainPanel();
        contentPanel.add(mainPanel, "MAIN");

        frame.getContentPane().add(contentPanel);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username field
        JComboBox<String> usernameCombo = new JComboBox<>(new String[]{"Khanh", "Chris", "Hilario", "Admin"});
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        panel.add(usernameCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String username = (String) usernameCombo.getSelectedItem();
            String password = new String(passwordField.getPassword());
            Account account = loginController.login(username, password);
            
            if (account != null) {
                CardLayout cl = (CardLayout) contentPanel.getLayout(); // Use the class-level variable
                cl.show(contentPanel, "MAIN");
                userLabel.setText("Logged in as: " + username);
                refreshSongList();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid login credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        
        // Top panel with user info and search
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        
        // User info panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userLabel = new JLabel("Not logged in");
        JButton logoutButton = new JButton("Logout");
        userPanel.add(userLabel);
        userPanel.add(logoutButton);
        topPanel.add(userPanel, BorderLayout.NORTH);
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add Music");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        panel.add(topPanel, BorderLayout.NORTH);

        // Center - Song list
        resultList = new JList<>();
        panel.add(new JScrollPane(resultList), BorderLayout.CENTER);

        // Bottom - Playback controls
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        
        // Control buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        skipButton = new JButton("Skip");
        backButton = new JButton("Back");
        controlPanel.add(backButton);
        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(skipButton);
        bottomPanel.add(controlPanel, BorderLayout.NORTH);
        
        // Duration slider
        durationSlider = new JSlider(0, 100, 0);
        durationSlider.setPreferredSize(new Dimension(300, 20));
        bottomPanel.add(durationSlider, BorderLayout.SOUTH);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners
        searchButton.addActionListener(e -> {
            String filter = searchField.getText();
            List<Song> results = searchController.filterSongs(filter);
            updateResultList(results);
        });

        addButton.addActionListener(e -> {
            String filePath = JOptionPane.showInputDialog("Enter music file path:");
            addMusic.addFile(filePath);
            refreshSongList();
        });

        playButton.addActionListener(e -> {
            int selectedIndex = resultList.getSelectedIndex();
            if (selectedIndex >= 0 && currentSongs != null) {
                Song songToPlay = currentSongs.get(selectedIndex);
                playbackController.play(songToPlay);
            }
        });

        pauseButton.addActionListener(e -> playbackController.pause());
        skipButton.addActionListener(e -> playbackController.skip());
        backButton.addActionListener(e -> playbackController.back());

        logoutButton.addActionListener(e -> {
            loginController.logout();
            CardLayout cl = (CardLayout) contentPanel.getLayout(); // Correctly using contentPanel
            cl.show(contentPanel, "LOGIN");
            userLabel.setText("Not logged in");
            contentPanel.revalidate(); // Ensure the panel is revalidated
            contentPanel.repaint();    // Ensure the panel is repainted
        });

        durationSlider.addChangeListener(e -> {
            if (!durationSlider.getValueIsAdjusting() && playbackController.getCurrentSong() != null) {
                double duration = playbackController.getDuration();
                if (duration > 0) {
                    double newTime = (durationSlider.getValue() / 100.0) * duration;
                    playbackController.seek(newTime);
                }
            }
        });

        return panel;
    }

    private List<Song> currentSongs;

    private void updateResultList(List<Song> results) {
        this.currentSongs = results;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Song song : results) {
            listModel.addElement(song.getDetails());
        }
        resultList.setModel(listModel);
    }

    private void refreshSongList() {
        // Update the song list to show current user's songs
        updateResultList(searchController.getCurrentUserSongs());
    }
}
