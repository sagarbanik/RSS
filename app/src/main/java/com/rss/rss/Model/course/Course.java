package com.rss.rss.Model.course;

import java.util.List;

public class Course {
    private int id;
    private String name;
    private String description;
    private String course_duration;
    private float price;
    private String file_name;
    private List<CourseParts> course_parts;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCourse_duration() {
        return course_duration;
    }

    public float getPrice() {
        return price;
    }

    public String getFile_name() {
        return file_name;
    }

    public List<CourseParts> getCourse_parts() {
        return course_parts;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", course_duration='" + course_duration + '\'' +
                ", price=" + price +
                ", file_name='" + file_name + '\'' +
                ", course_parts=" + course_parts +
                '}';
    }
}
