package com.rss.rss.Model.course;

public class CourseParts {
    private int id;
    private int course_id;
    private String name;
    private String duration;
    private String link;
    private String description;

    public int getId() {
        return id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "CourseParts{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
