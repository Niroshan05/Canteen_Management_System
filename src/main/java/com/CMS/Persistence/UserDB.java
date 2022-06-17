package com.CMS.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.CMS.DBConnection;
import com.CMS.Model.Customer;
import com.CMS.Model.Vendor;
import com.CMS.Cli.CliMain;


public class UserDB {
    static int i;
    static Connection conn=null;
    static PreparedStatement stmt=null;
    static int DBUserID;
    static String DBPassword;
    
    public static int addUser(String cname, String pass, String cphone, String email, int walletamt) {
        try{
            conn=DBConnection.getDBConnection();
        stmt=conn.prepareStatement("insert into Customer(customerName,customerPhone,customerEmail,customerWalletBal,password) values(?,?,?,?,?)");
        stmt.setString(1,cname);
        stmt.setString(2, cphone);
        stmt.setString(3, email);
        stmt.setInt(4, walletamt);
        stmt.setString(5, pass);
        i=stmt.executeUpdate();

    
        }catch(SQLException e)
        {
            e.printStackTrace();
        } 
        catch(Exception e)
        {
            e.printStackTrace();
        }  
                return i;
              
    }
    public static String passwordCheckerForCustomer(int loginID,String password) {
         String custName=null;
         try{
           
            System.out.println(" checking pasword");
            conn=DBConnection.getDBConnection();
            stmt=conn.prepareStatement("Select * from Customer where customerid=?");
            stmt.setInt(1, loginID);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                DBUserID=rs.getInt("customerId");
                DBPassword=rs.getString("password");
                custName=rs.getString("customerName");
            }          
            while(loginID==DBUserID&&password.equals(DBPassword))  //|| !custName.equals(null)
            {
                System.out.println("login success");
                return custName;
            }
            System.out.println("Entered pasword is incorrect please try again");
            CliMain.login();
            
            
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        //return loginID;
        return null;
 
    }
    public static String passwordCheckerForVendor(int loginID, String password) {
        try{
            String custName=null;
            conn=DBConnection.getDBConnection();
            stmt=conn.prepareStatement("Select * from Vendor where vendorId=?");
            stmt.setInt(1, loginID);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                DBUserID=rs.getInt("vendorId");
                DBPassword=rs.getString("password");
                custName=rs.getString("vendorName");
            }
            while(loginID==DBUserID&&password.equals(DBPassword))  //|| !custName.equals(null)
            {
                System.out.println("login success");
                return custName;
                
            }
           
            System.out.println("Entered pasword is incorrect please try again");
            CliMain.login();
            
            
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return null;
        
    }

    /**
     * checking ordervalue and customer balance
     * @param cloginID
     * @param ovalue
     * @return
     */
      public static Object walletCheck(int cloginID,int ovalue){
       int customerbal=0; 
       String customerName="back";   
       customerbal=customerWallet(cloginID); 
       
        
        if(customerbal==0)
        {
            System.out.println(".......Wallet is Empty-Unable to Place Order.......");
            System.out.println("*******please add money to your wallet before ordering**");
            CliMain.cLogin(cloginID,customerName);
       
         }
         else if (customerbal<ovalue){
             System.out.println("balance is not sufficinet for the order");
             System.out.println("*****please add money to your wallet*****");
             CliMain.cLogin(cloginID,customerName);
         }
         else{
             System.out.println("placing your order");
             try{
                 int remainingabal=customerbal-ovalue;
                conn=DBConnection.getDBConnection();
                
                stmt=conn.prepareStatement("update Customer set customerWalletBal=? where customerId=?");
                stmt.setInt(1, remainingabal);
                stmt.setInt(2, cloginID);
                i=stmt.executeUpdate();
                System.out.println("Your remaining balance : "+remainingabal);
         }catch(Exception e){System.out.println(e);}
        }
        return null;

        
}

public static int customerWallet(int cloginID) {
    int customerbal=0;
    try{
        conn=DBConnection.getDBConnection();
        stmt=conn.prepareStatement("Select customerWalletBal from customer where customerId=?");
        stmt.setInt(1, cloginID);
        ResultSet rs=stmt.executeQuery();
        
        
        while(rs.next()){
            customerbal=rs.getInt("customerWalletBal");
            }
        return customerbal;
    }catch(Exception e){System.out.println(e);}
    return customerbal;
}
/**
 * vendor profile displayer
 */
    public static Vendor[] vendorProfile() {
        Vendor m[]=null;
        try{  
          conn=DBConnection.getDBConnection();
            Statement stmt=conn.createStatement();  
                    
            ResultSet rs=stmt.executeQuery("select * from Vendor");  
            ArrayList<Vendor> list=new ArrayList<Vendor>();          
            while(rs.next()) { 
            list.add(new Vendor(rs.getInt(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5)));
                m=new Vendor[list.size()];
                m= list.toArray(m);
                  } 
        }catch(Exception e){ System.out.println(e);}  
            
        return m; 
    }
public static Customer[] customerProfile(int cID, String customerName) {
    Customer m[]=null;
        try{  
          conn=DBConnection.getDBConnection();
            Statement stmt=conn.createStatement();  
                    
            ResultSet rs=stmt.executeQuery("select * from Customer");  
            ArrayList<Customer> list=new ArrayList<Customer>();          
            while(rs.next()) { 
            list.add(new Customer(rs.getInt(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
                m=new Customer[list.size()];
                m= list.toArray(m);
                  } 
        }catch(Exception e){ System.out.println(e);}  
            
        return m; 
    
}
public static void FoodPriceChanger(int fid, int fprice) {
    
    try{
        
       conn=DBConnection.getDBConnection();
       
       stmt=conn.prepareStatement("update menu set foodPrice=? where foodId=?");
       stmt.setInt(1, fprice);
       stmt.setInt(2, fid);
       i=stmt.executeUpdate();
       System.out.println("food price updated");
}catch(Exception e){System.out.println(e);}
}
public static void addnewdb(String foodName, int foodprice,int vId) {
    try{
        int foodid=0;
        conn=DBConnection.getDBConnection();
        stmt=conn.prepareStatement("select max(foodid) from menu");
        ResultSet a=stmt.executeQuery();
        while (a.next()) {
          foodid=a.getInt(1);  
        }
        
        foodid++;
        stmt=conn.prepareStatement("insert into menu (foodid,foodName,foodPrice,vendorId) values(?,?,?,?)");
        stmt.setInt(1, foodid);
        stmt.setString(2, foodName);
        stmt.setInt(3, foodprice);
        stmt.setInt(4,vId);
        i=stmt.executeUpdate();
        System.out.println("updated menu\n");
        CliMain.menuList();
 }catch(Exception e){System.out.println(e);}
}


}
   



    

