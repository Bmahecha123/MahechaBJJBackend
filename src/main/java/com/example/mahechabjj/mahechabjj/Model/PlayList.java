package com.example.mahechabjj.mahechabjj.Model;

import java.util.ArrayList;

public class PlayList {

  private String name;
  private String description;
  private ArrayList<Video> videos;

  public PlayList() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ArrayList<Video> getVideos() {
    if (videos == null){
      videos = new ArrayList<>();
    }
    return videos;
  }

  public void setVideos(ArrayList<Video> videos) {
    this.videos = videos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PlayList playList = (PlayList) o;

    return name.equals(playList.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
