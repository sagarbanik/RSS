package com.rss.rss.Model.course;

import java.util.List;

public class CourseResponse {
    private String status;
    private List<Course> data;

    public String getStatus() {
        return status;
    }

    public List<Course> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CourseResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
