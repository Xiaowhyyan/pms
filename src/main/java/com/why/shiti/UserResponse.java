package com.why.shiti;

public class UserResponse {
       private String message;
    private String code;

    public UserResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }


    @Override
    public String toString() {
        return "UserResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
