package com.example.mahechabjj.mahechabjj.Model;

public class Video {

  private String name;
  private String image;
  private String link;
  private String linkHd;
  private String description;

  public Video() {

  }

  public String getLinkHd() {
    return linkHd;
  }

  public void setLinkHd(String linkHd) {
    this.linkHd = linkHd;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
