package com.rss.rss.Model.other;

public class FreelanceSupportEngineer {
    private int user_id;
    private String father_name;
    private String mother_name;
    private String nid;
    private String expert_area;
    private String last_degree;
    private String last_empl_history;
    private String additional_info;
    private String cv;

    public int getUser_id() {
        return user_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public String getNid() {
        return nid;
    }

    public String getExpert_area() {
        return expert_area;
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
        return "FreelanceSupportEngineer{" +
                "user_id=" + user_id +
                ", father_name='" + father_name + '\'' +
                ", mother_name='" + mother_name + '\'' +
                ", nid='" + nid + '\'' +
                ", expert_area='" + expert_area + '\'' +
                ", last_degree='" + last_degree + '\'' +
                ", last_empl_history='" + last_empl_history + '\'' +
                ", additional_info='" + additional_info + '\'' +
                ", cv='" + cv + '\'' +
                '}';
    }
}
