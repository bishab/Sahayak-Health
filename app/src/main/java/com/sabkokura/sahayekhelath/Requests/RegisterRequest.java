package com.sabkokura.sahayekhelath.Requests;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("first_name")
    String first_name;

    @SerializedName("last_name")
    String last_name;

    @SerializedName("gender")
    String gender;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("address")
    String address;

    @SerializedName("date_of_birth")
    String date_of_birth;

    @SerializedName("contact_number")
    String contact_number;

    public RegisterRequest(String first_name, String last_name, String gender, String email, String password, String address, String date_of_birth, String contact_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.contact_number = contact_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
}






