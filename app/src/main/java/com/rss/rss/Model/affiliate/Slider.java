package com.rss.rss.Model.affiliate;

public class Slider {

    private int id;

    private int category_id;

    private String name;

    private String description;

    private String slug;

    private int is_published;

    private String slider_image;


    public Slider(int id, String name, String slider_image) {
        this.id = id;
        this.name = name;
        this.slider_image = slider_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getIs_published() {
        return is_published;
    }

    public void setIs_published(int is_published) {
        this.is_published = is_published;
    }

    public String getSlider_image() {
        return slider_image;
    }

    public void setSlider_image(String slider_image) {
        this.slider_image = slider_image;
    }

    @Override
    public String toString() {
        return "Slider{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", slug='" + slug + '\'' +
                ", is_published=" + is_published +
                ", slider_image='" + slider_image + '\'' +
                '}';
    }
}
