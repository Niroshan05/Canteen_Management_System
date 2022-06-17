package com.CMS.Factory;

import com.CMS.Model.Menu;
import com.CMS.Model.Order;
import com.CMS.Persistence.OrderDb;

/**
 * OrderFactory class used to fetch and insert data to database.
 * @author niroshan
 */
public class OrderFactory {
    
    public static int OrderFood(int fid,int fquan,int cid){//, int vid, String cid, int fname
        
       int result= OrderDb.insertDb(fid,fquan,cid);//,vid,cid,fname
       return result;
    }

    public static Menu[] fetchMenu(){
        Menu menu[]=OrderDb.fetchDb();
        return menu;
    }
    public static Menu[] fetchMenuVendor(int vID){
        Menu menu[]=OrderDb.fetchDbVendor(vID);
        return menu;
    }
    // public static Customer customerProfile(){}
    // public static Vendor vendorProfile(){}
/**
*customer order history
*/    
    public static Order[] customerOrderHistory(int cID){
	    Order order[]=OrderDb.customerOderHistoryDb(cID);
	    return order;
}
/**
*vendor order history
*/ 
     public static Order[] vendorOrderHistory(int vID){
        Order order[]=OrderDb.vendorOderHistoryDb(vID);
	    return order;
     }
    // public static String acceptRejectOrder(){}

public static Order[] vendorOrderHistoryToday(int vID,String vendorName) {
    Order order[]=OrderDb.vendorOderHistoryDbToday(vID,vendorName);
    return order;
}
}
