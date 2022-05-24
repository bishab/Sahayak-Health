package com.sabkokura.sahayekhelath.ModelClasses;

public class AmbulanceListModelsClass {
    String name;
    String Address;
    String ph_number;
    boolean isExpanded;

    public AmbulanceListModelsClass(String name, String address, String ph_number) {
        this.name = name;
        Address = address;
        this.ph_number = ph_number;
        this.isExpanded=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPh_number() {
        return ph_number;
    }

    public void setPh_number(String ph_number) {
        this.ph_number = ph_number;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
