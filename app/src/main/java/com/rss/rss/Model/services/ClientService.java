package com.rss.rss.Model.services;

public class ClientService {
    private int user_id;
    private  String service_type;
    private  String service_name;
    private  String company_name;
    private  String address;
    private  String phone;
    private  String email;
    private  String description;


    public int getUser_id() {
        return user_id;
    }

    public String getService_type() {
        return service_type;
    }

    public String getService_name() {
        return service_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ClientService{" +
                "user_id=" + user_id +
                ", service_type='" + service_type + '\'' +
                ", service_name='" + service_name + '\'' +
                ", company_name='" + company_name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
