package com.example.mahechabjj.mahechabjj.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String id;
    private String email;
    private String name;
    private String belt;
    private HashMap<String, String> secretQuestions;
    private String password;
    private ArrayList<PlayList> playlists;

    public User(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public HashMap<String, String> getSecretQuestions() {
        return secretQuestions;
    }

    public void setSecretQuestions(HashMap<String, String> secretQuestions) {
        this.secretQuestions = secretQuestions;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<PlayList> getPlaylists() {
        if (playlists == null){
            playlists = new ArrayList<>();
        }
        return playlists;
    }

    public void setPlaylists(ArrayList<PlayList> playlists) {
        this.playlists = playlists;
    }
}
