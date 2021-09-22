package com.rss.rss.Model.adpost.website_promotion;

public class WebPromotionPostResponse {
    private String status;
    private String heading;
    private String message;

    public String isSuccess() {
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
        return "ProductPromotionPostResponse{" +
                "success=" + status +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
