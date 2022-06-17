package com.CMS.Model;

public class Vendor {
    private int vendorId;
    private String vendorName;
    private long vendorPhone;
    private String vendorSpecs;
    private String password;
    
    public Vendor(){}

    public Vendor(int vendorId,String vendorName,long vendorPhone,String vendorSpecs,String password){
        this.vendorId=vendorId;
        this.vendorName=vendorName;
        this.vendorPhone=vendorPhone;
        this.vendorSpecs=vendorSpecs;
        this.password=password;


    }
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public long getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(long vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getVendorSpecs() {
        return vendorSpecs;
    }

    public void setVendorSpecs(String vendorSpecs) {
        this.vendorSpecs = vendorSpecs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }   

    public int getVendorId() {
        return vendorId;
    }
}




    

