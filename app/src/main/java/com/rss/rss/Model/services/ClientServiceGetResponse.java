package com.rss.rss.Model.services;

import java.util.List;

public class ClientServiceGetResponse {
    private String status;
    private String message;
    private List<ClientService> data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ClientService> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ClientServiceResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
