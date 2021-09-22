package com.rss.rss.Model.adpost.email_promotion;

public class EmailPromotionPostResponse {
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
        return "EmailPromotionPostResponse{" +
                "status='" + status + '\'' +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
