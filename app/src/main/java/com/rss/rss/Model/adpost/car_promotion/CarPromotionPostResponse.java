package com.rss.rss.Model.adpost.car_promotion;

public class CarPromotionPostResponse {
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
        return "CarPromotionPostResponse{" +
                "status='" + status + '\'' +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
