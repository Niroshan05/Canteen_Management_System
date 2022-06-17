package com.CMS.Model;

public class Customer {
    private int customerId;
    private String customerName;
    private long customerPhone;
    private String customerEmail;
    private int customerWalletBal;
    private String password;

    public Customer(){}

    public Customer(int customerId,String customerName,long customerPhone,String customerEmail,int customerWalletBal,String password){
        this.customerId=customerId;
        this.customerName=customerName;
        this.customerPhone=customerPhone;
        this.customerEmail=customerEmail;
        this.customerWalletBal=customerWalletBal;
        this.password=password;


    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public long getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(long customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public int getCustomerWalletBal() {
        return customerWalletBal;
    }
    public void setCustomerWalletBal(int customerWalletBal) {
        this.customerWalletBal = customerWalletBal;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
