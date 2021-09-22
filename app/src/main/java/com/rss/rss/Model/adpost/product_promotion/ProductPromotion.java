package com.rss.rss.Model.adpost.product_promotion;

public class ProductPromotion {
    private int user_id;
    private String product_name;
    private double ad_budget;
    private String shop_address;
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

    public String getShop_address() {
        return shop_address;
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
                ", shop_address='" + shop_address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", product_info='" + product_info + '\'' +
                '}';
    }
}
