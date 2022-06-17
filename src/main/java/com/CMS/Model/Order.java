package com.CMS.Model;

import java.sql.Timestamp;


/**
*used for customer and vendor order history
*@author niroshan
*/
public class Order{
    private int orderNo;
    private int vendorId;
    private int CustomerId;
    private int FoodId;    
    private String foodName;
    private int Qunatity;
    private String ETA; 
    private Timestamp orderPlacedAt;
    private int orderValue;    
    private String orderstatus;
    private String reason;
    
  
    public Order(){}

    public Order(int orderNo, int vendorId, int customerId, int foodId, String foodName, int foodQunatity, String ETA,
            Timestamp orderPlacedAt, int valueOFTheOrder, String orderstatus, String reason) {
        this.orderNo = orderNo;
        this.vendorId = vendorId;
        this.CustomerId = customerId;
        this.FoodId = foodId;
        this.foodName = foodName;
        this.Qunatity = foodQunatity;
        this.ETA = ETA;
        this.orderPlacedAt = orderPlacedAt;
        this.orderValue = valueOFTheOrder;
        this.orderstatus = orderstatus;
        this.reason = reason;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }


    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }


    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }


    public void setFoodId(int getFoodId) {
        this.FoodId = getFoodId;
    }

    public void setFoodname(String foodname) {
        this.foodName = foodname;
    }


    public void setFoodQunatity(int Qunatity) {
        this.Qunatity = Qunatity;
    }


    public void setETA(String ETA) {
        this.ETA = ETA;
    }


    public void setOrderPlacedTime(Timestamp orderPlacedAt) {
        this.orderPlacedAt = orderPlacedAt;
    }


    public void setValueOFTheOrder(int valueOFTheOrder) {
        this.orderValue = valueOFTheOrder;
    }


    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }


    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }
    public String getOrderstatus() {
        return orderstatus;
    }
    public int getValueOFTheOrder() {
        return orderValue;
    }
    public Timestamp getOrderPlacedTime() {
        return orderPlacedAt;
    }
    public String getETA() {
        return ETA;
    }
    public int getFoodQunatity() {
        return Qunatity;
    }
    public String getFoodname() {
        return foodName;
    }
    public int getFoodId() {
        return FoodId;
    }
    public int getCustomerId() {
        return CustomerId;
    }
    public int getOrderNo() {
        return orderNo;
    }
    public int getVendorId() {
        return vendorId;
    }


    // @Override
    public String toString() {
        return "CustomerId" + CustomerId + "ETA=" + ETA + ", FoodId" + FoodId + "foodName" + foodName
                + "foodQunatity=" + Qunatity + "orderNo=" + orderNo + "orderPlacedTime=" + orderPlacedAt
                + "orderstatus=" + orderstatus + "reason=" + reason + "valueOFTheOrder=" + orderValue
                + "vendorId=" + vendorId ;
    }


}