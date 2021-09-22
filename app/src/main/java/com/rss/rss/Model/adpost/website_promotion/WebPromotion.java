package com.rss.rss.Model.adpost.website_promotion;

public class WebPromotion {
    private int user_id;
    private String product_name;
    private double ad_budget;
    private String web_address;
    private String email;
    private String phone;
    private String product_info;

    public int getUser_id() {
        return user_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getAd_budget() {
        return ad_budget;
    }

    public String getWeb_address() {
        return web_address;
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
                ", product_name='" + product_name + '\'' +
                ", ad_budget=" + ad_budget +
                ", shop_address='" + web_address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", product_info='" + product_info + '\'' +
                '}';
    }
}
