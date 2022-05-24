package com.sabkokura.sahayekhelath.ModelClasses;

public class HospitalListModelsClass {
    String hospitalName;
    String hospitalAddress;
    String hospitalPhone;
    String hospitalDescription;
    String hospitalLink;
    boolean isExpanded;



    public HospitalListModelsClass(String hospitalName, String hospitalAddress, String hospitalPhone, String hospitalDescription, String hospitalLink) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalPhone = hospitalPhone;
        this.hospitalDescription = hospitalDescription;
        this.hospitalLink = hospitalLink;
        this.isExpanded=false;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public String getHospitalDescription() {
        return hospitalDescription;
    }

    public void setHospitalDescription(String hospitalDescription) {
        this.hospitalDescription = hospitalDescription;
    }

    public String getHospitalLink() {
        return hospitalLink;
    }

    public void setHospitalLink(String hospitalLink) {
        this.hospitalLink = hospitalLink;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
