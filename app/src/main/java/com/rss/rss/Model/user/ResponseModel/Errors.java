package com.rss.rss.Model.user.ResponseModel;

import java.util.List;

public class Errors {

    private List<String> user_name;

    public void setUser_name(List<String> user_name) {

        this.user_name = user_name;

    }

    public List<String> getUser_name() {

        return this.user_name;

    }

    @Override
    public String toString() {
        return "Errors{" +
                "user_name=" + user_name +
                '}';
    }
}
