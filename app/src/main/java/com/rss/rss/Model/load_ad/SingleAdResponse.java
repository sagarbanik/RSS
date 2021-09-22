package com.rss.rss.Model.load_ad;

import java.util.List;

public class SingleAdResponse {
    private String status;
    private Ad data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ad getData() {
        return data;
    }

    public void setData(Ad data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SingleAdResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
