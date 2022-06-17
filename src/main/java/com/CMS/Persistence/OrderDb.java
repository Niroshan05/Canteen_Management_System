package com.CMS.Persistence;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.CMS.DBConnection;
import com.CMS.Cli.CliMain;
import com.CMS.Model.Menu;
import com.CMS.Model.Order;
//import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

import java.sql.PreparedStatement;

/**
 * OrderDb class used to connect to data base.
 * @author niroshan
 */
public class OrderDb {
   static Scanner sc=new Scanner(System.in);
  static int i;
   static Connection conn=null;
 
   
    public static int insertDb(int fid,int fq, int cid){     
         PreparedStatement stmt=null;
         String customerName="back";
         try{
          conn=DBConnection.getDBConnection();
      //create a method to get the total order value
             int ovalue = ordervalue(fid,fq);
            //  System.out.println("ovalue= "+ovalue);
      //create a method to check ordervalue<= walletbalance
             UserDB.walletCheck(cid, ovalue);
          stmt=conn.prepareStatement("insert into orderdetails (vendorId,customerId,foodId,foodName,quantity,ETA,orderPlacedAt,orderValue,orderStatus) values((select vendorId from menu where foodId=?),?,?,(select foodName from menu where foodId=?),?,?,now(),?*(select foodprice from menu where foodID=?),?)");  
        stmt.setInt(1,fid);  
        stmt.setInt(2,cid);  
        stmt.setInt(3,fid);
        stmt.setInt(4,fid);   
        stmt.setInt(5,fq);
        stmt.setString(6, "0.20.00");
        stmt.setInt(7, fq);
        stmt.setInt(8, fid);
        stmt.setString(9, "waiting");
        i=stmt.executeUpdate();  
        System.out.println("enter something to go back to customer login");
        sc.next();
        CliMain.cLogin(cid, customerName);
        }catch(Exception e){
           System.out.println(e);}  
                return i;
    }  
////get the order value   
    private static int ordervalue(int foodid,int foodquantiy) {
      PreparedStatement stmt=null;
      int foodprice=0;
      int foodvalue=1;
      try{
         conn=DBConnection.getDBConnection();
         stmt=conn.prepareStatement("select foodprice from menu where foodID=?");
         stmt.setInt(1,foodid);
         ResultSet rs = stmt.executeQuery();
         for (; rs.next();) {
          foodprice =  (Integer) rs.getObject(1);
          }
         foodvalue=foodprice*foodquantiy;
         System.out.println("total order value is "+foodvalue);
         return foodvalue;
      }
      catch(Exception e){System.out.println(e);}
      
      return foodvalue;
            
    }

    public static Menu[] fetchDb(){
        Menu m[]=null;
       
        try{  
          conn=DBConnection.getDBConnection();
            Statement stmt=conn.createStatement();  
                    
            ResultSet rs=stmt.executeQuery("select * from menu");  
            ArrayList<Menu> list=new ArrayList<Menu>();          
            while(rs.next()) { 
            list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
                m=new Menu[list.size()];
                m= list.toArray(m);
                  } 
        }catch(Exception e){ System.out.println(e);}  
            
        return m;      
        }

      
/**
*cust order history
*/

public static Order[] customerOderHistoryDb(int cID){
	Order o[]=null;
  PreparedStatement stmt=null;
	try{  
    conn=DBConnection.getDBConnection();
    stmt=conn.prepareStatement("select * from orderdetails where customerId=?");
    stmt.setInt(1,cID);
    ResultSet rs = stmt.executeQuery();           
            //System.out.println(rs+"select*");
            ArrayList<Order> list=new ArrayList<Order>();          
      for(;rs.next();) {           
        list.add(new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getTimestamp(8),rs.getInt(9),rs.getString(10),rs.getString(11)));
                o=new Order[list.size()];
                o= list.toArray(o);
                //return o;
                  } 
        }catch(Exception e){System.out.println(e);}  
            
        return o; 

}
/**
 * VENDOR ORDER HISTORY
 * o =list that was created in Order.java 
 */
       public static Order[] vendorOderHistoryDb(int vID){
        Order o[]=null;
        PreparedStatement stmt=null;
        try{  
          conn=DBConnection.getDBConnection();
          stmt=conn.prepareStatement("select * from orderdetails where vendorId=?");
          stmt.setInt(1,vID);
          ResultSet rs = stmt.executeQuery();           
                  //System.out.println(rs+"select*");
                  ArrayList<Order> list=new ArrayList<Order>();          
            for(;rs.next();) {       
              list.add(new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getTimestamp(8),rs.getInt(9),rs.getString(10),rs.getString(11)));
                      o=new Order[list.size()];
                      o= list.toArray(o);
                      //return o;
                        } 
              }catch(Exception e){System.out.println(e);}  
                  
              return o; 
       }
       public static Order[] vendorOderHistoryDbToday(int vID,String vendorName){
        Order o[]=null;
        PreparedStatement stmt=null;
        try{  
          conn=DBConnection.getDBConnection();
          stmt=conn.prepareStatement("select * from orderdetails where vendorId=? &&  orderplacedat>=curdate() && orderstatus='waiting'");
          stmt.setInt(1,vID);
          ResultSet rs = stmt.executeQuery();
          if (rs==null){
            System.out.println("there is no order today");
            CliMain.vlogin(vID,vendorName);
          }           
                  //System.out.println(rs+"select*");
                  ArrayList<Order> list=new ArrayList<Order>();          
            for(;rs.next();) {       
              list.add(new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getTimestamp(8),rs.getInt(9),rs.getString(10),rs.getString(11)));
                      o=new Order[list.size()];
                      o= list.toArray(o);
                      //return o;
                        } 
                      }
                      catch(SQLException e)
        {
            System.out.print("NullPointerException Caught");
        }
              //catch(Exception e){System.out.println("ecption"+e);}
               
                  
              return o; 
       }
       public static void acceptRejectDb(int vID){
         PreparedStatement stmt=null;
         Order[] o;
        try{  
          conn=DBConnection.getDBConnection();
          stmt=conn.prepareStatement("select * from orderdetails where vendorId=?");
          stmt.setInt(1,vID);
          ResultSet rs = stmt.executeQuery();           
                  //System.out.println(rs+"select*");
                  ArrayList<Order> list=new ArrayList<Order>();          
            for(;rs.next();) {       
              list.add(new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getTimestamp(8),rs.getInt(9),rs.getString(10),rs.getString(11)));
                      o=new Order[list.size()];
                      o= list.toArray(o);
                      //return o;
                        } 
              }catch(Exception e){System.out.println(e);}  
                  
              return; 
        
       }
    public static void classupdateOrder(int onum, String AorR, String reason) {
      PreparedStatement stmt=null;
         //Order[] o;
         if (AorR.equals("A")){
           AorR="Accepted";
         }
         else{
           AorR="rejected sorry";
         }
        try{  
          conn=DBConnection.getDBConnection();
          stmt=conn.prepareStatement("update orderdetails set orderStatus=?,reason=? where orderNo=?");
          stmt.setString(1, AorR);
          stmt.setString(2, reason);
          stmt.setInt(3,onum);
          i= stmt.executeUpdate();
    }catch(Exception e){System.out.println(e);}
    }
    public static Menu[] fetchDbVendor(int vID) {
      Menu m[]=null;
       
        try{  
          conn=DBConnection.getDBConnection();
            PreparedStatement stmt=null;  
            stmt=conn.prepareStatement("select * from menu where vendorId=?");
            stmt.setInt(1, vID);
            ResultSet rs = stmt.executeQuery();            
            ArrayList<Menu> list=new ArrayList<Menu>();          
            while(rs.next()) { 
            list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
                m=new Menu[list.size()];
                m= list.toArray(m);
                  } 
        }catch(Exception e){ System.out.println(e);}  
            
        return m;    
      
    }
    
  }

