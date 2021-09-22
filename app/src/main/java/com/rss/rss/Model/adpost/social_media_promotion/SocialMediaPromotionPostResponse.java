package com.rss.rss.Model.adpost.social_media_promotion;

public class SocialMediaPromotionPostResponse {
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
        return "SocialMediaPromotionPostResponse{" +
                "status='" + status + '\'' +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
