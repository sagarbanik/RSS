package com.rss.rss.Model.affiliate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ShippingAddress {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "u_id")
    private int u_id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "details")
    private String details;

    public ShippingAddress(int u_id, String title, String details) {
        this.u_id = u_id;
        this.title = title;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + id +
                "u_id"+ u_id +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}

