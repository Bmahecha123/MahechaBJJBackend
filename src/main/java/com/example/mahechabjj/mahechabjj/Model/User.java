package com.example.mahechabjj.mahechabjj.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String id;
    private String email;
    private String name;
    private String belt;
    private String secretQuestion;
    private String secretQuestionAnswer;
    private String password;
    private ArrayList<PlayList> playlists;
    private Packages packages;

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

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretQuestionAnswer() {
        return secretQuestionAnswer;
    }

    public void setSecretQuestionAnswer(String secretQuestionAnswer) {
        this.secretQuestionAnswer = secretQuestionAnswer;
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

    public Packages getPackages() {
        return packages;
    }

    public void setPackages(Packages packages) {
        this.packages = packages;
    }

    public class Packages {
        private boolean noGiJiuJitsu;
        private boolean giJiuJitsu;
        private boolean giAndNoGiJiuJitsu;

        public boolean isNoGiJiuJitsu() {
            return noGiJiuJitsu;
        }

        public void setNoGiJiuJitsu(boolean noGiJiuJitsu) {
            this.noGiJiuJitsu = noGiJiuJitsu;
        }

        public boolean isGiJiuJitsu() {
            return giJiuJitsu;
        }

        public void setGiJiuJitsu(boolean giJiuJitsu) {
            this.giJiuJitsu = giJiuJitsu;
        }

        public boolean isGiAndNoGiJiuJitsu() {
            return giAndNoGiJiuJitsu;
        }

        public void setGiAndNoGiJiuJitsu(boolean giAndNoGiJiuJitsu) {
            this.giAndNoGiJiuJitsu = giAndNoGiJiuJitsu;
        }
    }
}
