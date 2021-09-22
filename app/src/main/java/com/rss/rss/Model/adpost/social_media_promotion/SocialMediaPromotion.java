package com.rss.rss.Model.adpost.social_media_promotion;

public class SocialMediaPromotion {
    private int user_id;
    private String social_media_name;
    private double ad_budget;
    private String link;
    private String email;
    private String phone;
    private String product_info;

    public int getUser_id() {
        return user_id;
    }

    public String getSocial_media_name() {
        return social_media_name;
    }

    public double getAd_budget() {
        return ad_budget;
    }

    public String getLink() {
        return link;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getProduct_info() {
        return product_info;
    }

    @Override
    public String toString() {
        return "ProductPromotion{" +
                "user_id=" + user_id +
                ", product_name='" + social_media_name + '\'' +
                ", ad_budget=" + ad_budget +
                ", shop_address='" + link + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", product_info='" + product_info + '\'' +
                '}';
    }
}
