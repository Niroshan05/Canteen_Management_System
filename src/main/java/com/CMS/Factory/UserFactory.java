package com.CMS.Factory;

import com.CMS.Model.Customer;
import com.CMS.Model.Vendor;
import com.CMS.Persistence.UserDB;
import com.CMS.Persistence.OrderDb;
import com.CMS.Persistence.OrderDb;


public class UserFactory {

    public static int CreateUser(String cname, String pass, String cphone, String email, int walletamt) {
        int result= UserDB.addUser(cname,pass,cphone,email,walletamt);
       return result;
        
    }

    public static String userAutheniticator(int lID, String pASS) {
        if(lID>=1000){
       String result=UserDB.passwordCheckerForCustomer(lID,pASS);
       return result;
        }
        else{
            String result=UserDB.passwordCheckerForVendor(lID, pASS);
            return result;
        }
    }

    public static Vendor[] fetchVendorProfile() {
       
            Vendor vendor[]=UserDB.vendorProfile();
            return vendor;
        
        
    }

    public static Customer[] fetchCustomerProfile(int cID,String customerName) {
        Customer customer[]=UserDB.customerProfile(cID,customerName);
        return customer;
    }

    public static void acceptRejectOrder(int vID )
    {
        OrderDb.acceptRejectDb(vID);
        //return result;
    }
    // public static int acceptAndReject2(int fid,int fquantity,int cId)
    // {
    //     int result=UserDB.walletcheck2(fid,fquantity,cId);
    //     return result;
    // }

   
    
}
