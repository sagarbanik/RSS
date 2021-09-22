package com.rss.rss.Model.affiliate;

public class Express {
    private int id;
    private String title;
    private String image;

    public Express(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Express() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Express{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
