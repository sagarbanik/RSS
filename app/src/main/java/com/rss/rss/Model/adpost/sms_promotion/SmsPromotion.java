package com.rss.rss.Model.adpost.sms_promotion;

public class SmsPromotion {
    private int user_id;
    private String category;
    private double child_category;
    private String phone;
    private String email;
    private String additional_info;

    public int getUser_id() {
        return user_id;
    }

    public String getCategory() {
        return category;
    }

    public double getChild_category() {
        return child_category;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    @Override
    public String toString() {
        return "SmsPromotion{" +
                "user_id=" + user_id +
                ", category='" + category + '\'' +
                ", child_category=" + child_category +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", additional_info='" + additional_info + '\'' +
                '}';
    }
}
