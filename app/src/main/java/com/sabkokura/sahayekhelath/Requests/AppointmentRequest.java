package com.sabkokura.sahayekhelath.Requests;

import com.google.gson.annotations.SerializedName;

public class AppointmentRequest {
    @SerializedName("firstname")
    String firstName;

    @SerializedName("lastname")
    String lastName;

    @SerializedName("age")
    String age;

    @SerializedName("patient_email")
    String patientEmail;

    @SerializedName("patient_address")
    String patientAddress;

    @SerializedName("gender")
    String patientGender;

    @SerializedName("contact_number")
    String hospitalName;

    @SerializedName("department")
    String hospitalDepartment;

    @SerializedName("date")
    String eventDate;

    @SerializedName("time")
    String eventTime;

    @SerializedName("previous_report")
    String previoudReport;


    public AppointmentRequest(String firstName, String lastName, String age, String patientEmail, String patientAddress, String patientGender,
                              String hospitalName, String hospitalDepartment, String eventDate, String eventTime, String previoudReport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.patientEmail = patientEmail;
        this.patientAddress = patientAddress;
        this.patientGender = patientGender;
        this.hospitalName = hospitalName;
        this.hospitalDepartment = hospitalDepartment;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.previoudReport = previoudReport;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalDepartment() {
        return hospitalDepartment;
    }

    public void setHospitalDepartment(String hospitalDepartment) {
        this.hospitalDepartment = hospitalDepartment;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getPrevioudReport() {
        return previoudReport;
    }

    public void setPrevioudReport(String previoudReport) {
        this.previoudReport = previoudReport;
    }
}
