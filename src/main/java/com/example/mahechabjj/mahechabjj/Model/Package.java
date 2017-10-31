package com.example.mahechabjj.mahechabjj.Model;

import java.util.List;

public class Package {

  private String id;
  private String name;
  private String description;
  private String price;
  private Content content;

  public Package() {

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Content getContent() {
    return content;
  }

  public void setContent(Content content) {
    this.content = content;
  }

  public static class Content {

    List<Video> videos;
    List<PlayList> playLists;

    public Content() {

    }

    public List<Video> getVideos() {
      return videos;
    }

    public void setVideos(List<Video> videos) {
      this.videos = videos;
    }

    public List<PlayList> getPlayLists() {
      return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
      this.playLists = playLists;
    }
  }
}
