package com.rss.rss.Model.user.ResponseModel;

import com.google.gson.annotations.SerializedName;
import com.rss.rss.Model.user.User;

public class LoginResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("user")
    private User user;

    @SerializedName("errors")
    private Errors errors;


    public String getMessage() {
        return message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public User getUser() {
        return user;
    }

    public Errors getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", access_token='" + access_token + '\'' +
                ", user=" + user +
                ", errors=" + errors +
                '}';
    }
}
