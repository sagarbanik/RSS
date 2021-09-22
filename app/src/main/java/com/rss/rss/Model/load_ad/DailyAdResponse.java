package com.rss.rss.Model.load_ad;

import java.util.Arrays;
import java.util.List;

public class DailyAdResponse {
    private String status;
    private List<Integer> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DailyAdResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
