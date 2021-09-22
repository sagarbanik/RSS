package com.rss.rss.Model.user;

public class User {

    private int id;
    private String name;
    private String user_name;
    private String birthday;
    private String email;
    private String phone;
    private int sponsor_id;
    private int package_id;
    private String email_verified_at;
    private String created_at;
    private String updated_at;
    private int affiliate_earning;
    private int ad_view_earning;
    private int sponsor_bonus;
    private int refer_earning;
    private int total_earning;
    private String reference_code;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getSponsor_id() {
        return sponsor_id;
    }

    public int getPackage_id() {
        return package_id;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getAffiliate_earning() {
        return affiliate_earning;
    }

    public int getAd_view_earning() {
        return ad_view_earning;
    }

    public int getSponsor_bonus() {
        return sponsor_bonus;
    }

    public int getRefer_earning() {
        return refer_earning;
    }

    public int getTotal_earning() {
        return total_earning;
    }

    public String getReference_code() {
        return reference_code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sponsor_id=" + sponsor_id +
                ", package_id=" + package_id +
                ", email_verified_at='" + email_verified_at + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", affiliate_earning=" + affiliate_earning +
                ", ad_view_earning=" + ad_view_earning +
                ", sponsor_bonus=" + sponsor_bonus +
                ", refer_earning=" + refer_earning +
                ", total_earning=" + total_earning +
                ", reference_code='" + reference_code + '\'' +
                '}';
    }
}
