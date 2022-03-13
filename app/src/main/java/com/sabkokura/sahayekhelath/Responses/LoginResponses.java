package com.sabkokura.sahayekhelath.Responses;

import com.google.gson.annotations.SerializedName;

public class LoginResponses {
    private String msg;

    // Getter Methods
    public String getStatus() {
        return msg;
    }

    // Setter Methods

    public void setStatus(String status) {
        this.msg = status;
    }
}
