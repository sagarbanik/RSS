package com.rss.rss.Model.other;

public class JobPlacementPostResponse {
    private String status;
    private String heading;
    private String message;


    public String getStatus() {
        return status;
    }

    public String getHeading() {
        return heading;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "JobPlacementPostResponse{" +
                "status='" + status + '\'' +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
