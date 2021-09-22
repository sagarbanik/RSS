package com.rss.rss.Model.adpost.sms_promotion;

public class SmsPromotionPostResponse {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SmsPromotionPostResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
