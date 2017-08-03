package com.example.mahechabjj.mahechabjj.Model;

import java.util.ArrayList;

public class PlayList {

  public String name;
  public ArrayList<Video> videos;

  public PlayList() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Video> getVideos() {
    return videos;
  }

  public void setVideos(ArrayList<Video> videos) {
    this.videos = videos;
  }
}
