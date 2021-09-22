package com.rss.rss.Model.other;

public class JobPlacement {
    private String user_id;
    private String f_name;
    private String l_name;
    private String email;
    private String phone;
    private String last_degree;
    private String last_empl_history;
    private String additional_info;
    private String cv;

    public String getUser_id() {
        return user_id;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLast_degree() {
        return last_degree;
    }

    public String getLast_empl_history() {
        return last_empl_history;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public String getCv() {
        return cv;
    }

    @Override
    public String toString() {
        return "JobPlacement{" +
                "user_id='" + user_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", last_degree='" + last_degree + '\'' +
                ", last_empl_history='" + last_empl_history + '\'' +
                ", additional_info='" + additional_info + '\'' +
                ", cv='" + cv + '\'' +
                '}';
    }
}
