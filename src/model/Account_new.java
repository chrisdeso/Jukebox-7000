package model;

import java.util.List;
import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private List<Song> songs;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.songs = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public List<Song> getSongs() { return songs; }
