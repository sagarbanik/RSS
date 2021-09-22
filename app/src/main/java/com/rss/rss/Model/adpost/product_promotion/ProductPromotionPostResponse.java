package com.rss.rss.Model.adpost.product_promotion;

public class ProductPromotionPostResponse {
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
