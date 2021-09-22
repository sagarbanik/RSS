package com.rss.rss.Model.load_ad;

public class Ad {
    private int id;
    private String name;
    private String description;
    private String ad_date;
    private String link;
    private String total_budget;
    private String total_impression;
    private String publish;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAd_date() {
        return ad_date;
    }

    public void setAd_date(String ad_date) {
        this.ad_date = ad_date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTotal_budget() {
        return total_budget;
    }

    public void setTotal_budget(String total_budget) {
        this.total_budget = total_budget;
    }

    public String getTotal_impression() {
        return total_impression;
    }

    public void setTotal_impression(String total_impression) {
        this.total_impression = total_impression;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ad_date='" + ad_date + '\'' +
                ", link='" + link + '\'' +
                ", total_budget='" + total_budget + '\'' +
                ", total_impression='" + total_impression + '\'' +
                ", publish='" + publish + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
