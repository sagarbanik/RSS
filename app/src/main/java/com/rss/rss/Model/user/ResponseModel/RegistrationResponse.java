package com.rss.rss.Model.user.ResponseModel;

public class RegistrationResponse {
    private boolean success;
    private String heading;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getHeading() {
        return heading;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "success=" + success +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
