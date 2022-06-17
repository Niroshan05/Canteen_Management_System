package com.CMS.Model;

/**
 * food class used to display food information.
 * @author hexware
 */
public class Menu {
    private int foodId;
    private String foodName;
    private int foodPrice;
    private int vendorId;

    public Menu(){}

    public Menu(int foodId,String foodName, int foodPrice,int vendorId){
        this.foodId=foodId;
        this.foodName=foodName;
        this.foodPrice=foodPrice;
        this.vendorId=vendorId;

    }

    public void setFoodId(int foodId){
        this.foodId=foodId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getFoodId(){
        return foodId;
    }

        public void setFoodName(String foodName){
            this.foodName=foodName;
        }

        public String getFoodName(){
            return foodName;
        }

            public void setFoodPrice(int foodPrice){
                this.foodPrice=foodPrice;
            }

            public int getFoodPrice(){
                return foodPrice;
            }
    public String toString(){
        return "food id:"+foodId+"food Name:"+foodName+"food Price"+foodPrice+"vendor Id"+vendorId;
    }
}


// public static Menu[] fetchDb(){
//     Menu m[]=null;
//     try{  
//       conn=DBConnection.getDBConnection();
//         Statement stmt=conn.createStatement();  
                
//         ResultSet rs=stmt.executeQuery("select * from menu");  
//         ArrayList<Menu> list=new ArrayList<Menu>();          
//         while(rs.next()) { 
//         list.add(new Menu(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
//             m=new Menu[list.size()];
//             m= list.toArray(m);
//               } 
//     }catch(Exception e){ System.out.println(e);}  
        
//     return m;      
//     }