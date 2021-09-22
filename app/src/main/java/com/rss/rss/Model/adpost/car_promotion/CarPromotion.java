package com.rss.rss.Model.adpost.car_promotion;

public class CarPromotion {
    private int user_id;
    private String category;
    private double child_category;
    private String company_name;
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

    public String getCompany_name() {
        return company_name;
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
        return "CarPromotion{" +
                "user_id=" + user_id +
                ", category='" + category + '\'' +
                ", child_category=" + child_category +
                ", company_name='" + company_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", additional_info='" + additional_info + '\'' +
                '}';
    }
}
