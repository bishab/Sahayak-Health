package com.sabkokura.sahayekhelath.ModelClasses;

public class HospitalListModelsClass {
    String hospitalName;
    String hospitalAddress;
    String hospitalPhone;
    String hospitalBeds;
    String hospitalVentilators;
    boolean isExpanded;



    public HospitalListModelsClass(String hospitalName, String hospitalAddress, String hospitalPhone, String hospitalBeds, String hospitalVentilators) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalPhone = hospitalPhone;
        this.hospitalBeds = hospitalBeds;
        this.hospitalVentilators = hospitalVentilators;
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

    public String getHospitalBeds() {
        return hospitalBeds;
    }

    public void setHospitalBeds(String hospitalBeds) {
        this.hospitalBeds = hospitalBeds;
    }

    public String getHospitalVentilators() {
        return hospitalVentilators;
    }

    public void setHospitalVentilators(String hospitalVentilators) {
        this.hospitalVentilators = hospitalVentilators;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
