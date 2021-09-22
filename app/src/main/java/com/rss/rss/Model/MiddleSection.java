package com.rss.rss.Model;

public class MiddleSection {
    private int id;
    private String caption;
    private String iconUrl;

    public MiddleSection(int id, String caption, String iconUrl) {
        this.id = id;
        this.caption = caption;
        this.iconUrl = iconUrl;
    }

    public MiddleSection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
