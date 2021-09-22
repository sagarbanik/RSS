package com.rss.rss.Model.affiliate;


public class FeatureTopic {

    private int id;
    private String name;
    private String slug;
    private String description;
    private int is_home;
    private String image;

    public FeatureTopic(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_home() {
        return is_home;
    }

    public void setIs_home(int is_home) {
        this.is_home = is_home;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FeatureCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", is_home=" + is_home +
                ", image='" + image + '\'' +
                '}';
    }
}
