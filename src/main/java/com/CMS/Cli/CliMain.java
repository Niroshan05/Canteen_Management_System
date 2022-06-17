package com.CMS.Cli;

import java.util.Scanner;

import com.CMS.Factory.OrderFactory;
import com.CMS.Factory.UserFactory;
import com.CMS.Model.Customer;
import com.CMS.Model.Menu;
import com.CMS.Model.Order;
import com.CMS.Model.Vendor;
import com.CMS.Persistence.OrderDb;
import com.CMS.Persistence.UserDB;

/**
 * CliMain used as Client interface for java coading.
 * 
 * @author niroshan
 */
public class CliMain {

    static Scanner sc = new Scanner(System.in);

    /**
     * main method used to display the option we had in the application.
     * 
     * @return
     */
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("Canteen Management System");
        System.out.println("----------------------------");
        System.out.println("1.For User Login");
        System.out.println("2.For Registering new User");
        System.out.println("3.Change password"); // half
        System.out.println("4.for Logging out"); // completed

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                login(); // completed
                break;
            case 2:
                newUser(); // completed
                break;
            case 3:
                changePassword(); // have to create
                break;
            case 4:
                Runtime.getRuntime().halt(0);
            default:
                System.out.println("Choose from the options 1 |or| 2 |or| 3");
        }

        /**
         * ########################
         * AFTER Logging IN I migth have to create a method since both ven& cust use
         * smae login function
         * ##########################
         */

    }

    /**
     * password match checker
     * 
     * @return
     */
    public static String passwordMatch() {
        String checkpass;
        String pass;

        do {
            System.out.println(
                    "enter a new password ##if your seeing this again you might have entered a wrong password");
            pass = sc.next();
            System.out.println("enter your password again");
            checkpass = sc.next();
        } while (!checkpass.equals(pass));
        return pass;
    }

    /**
     * user creating
     */
    public static void newUser() {
        System.out.println("enter your name: ");
        String cname = sc.next();
        String pass = passwordMatch(); // i called the function please check it
        System.out.println("Enter your moblie number:");
        String cphone = sc.next();
        System.out.println("enetr your E-Mail:");
        String email = sc.next();
        System.out.println("enter  your wallet amount");
        int walletamt = sc.nextInt();
        int r = UserFactory.CreateUser(cname, pass, cphone, email, walletamt);
        System.out.println(r + "   is inserted.....");

    }

    /**
     * Login
     */
    public static void login() {

        System.out.println("Please enter user id &password \n Enter Costomer/Vendor ID:");
        int lID = sc.nextInt();
        System.out.println("Enter Password");
        String pASS = sc.next();

        String r = UserFactory.userAutheniticator(lID, pASS);
        if (lID >= 1000) {
            cLogin(lID, r);
        } else {
            vlogin(lID, r);
        }
        System.out.println(r);

    }

    /**
     * cHANGE PASSWORD
     */
    public static void changePassword() {
        System.out.println("enter the user id: ");
        String lID = sc.next();
        System.out.println("enter E-Mail id for verification: ");
        String mID = sc.next();
        // CREATE AUTH BY CHECKING "SELECT <SOMETHING> FROM CUSTOMER WHERE CUSTOMERID IN
        // (?) AND PASSWORD IN(?)" <SOME..>.SETsTRING(1,LID) <SOME..>.SETSTRING(2,PASS);
        // if present i.e email
        String pass = passwordMatch();
    }

    /**
     * vendor login
     */
    public static void vlogin(int vID, String vendorName) {
        System.out.println("----------------------------");
        System.out.println("Canteen Management System");
        System.out.println("----------------------------");
        System.out.println("Customer ID : " + vID);
        System.out.println("----------------------------");
        System.out.println("welcome : " + vendorName);
        System.out.println("----------------------------");
        System.out.println("Enter your choice....");
        System.out.println("1.Show Menu");
        System.out.println("2.Accept & reject orders");
        System.out.println("3.order History");
        System.out.println("4.Edit menu");
        System.out.println("5.Profile");
        System.out.println("6.Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                menuListVendor(vID,vendorName);
                break;
            case 2:
                acceptRejectOrder(vID,vendorName); // 2. acceptAndReject(); need to create
                break;
            case 3:
                VendorOrderHistory(vID); // 3. need to create if u create one(cut or vendor) profile then u can copy and
                                         // edit it for another
                break;
            case 4:
                editMenu(vID, vendorName);
                break; // 4. editMenu();need to create
            case 5:
                vendorProfile(vID, vendorName);
                break;
            case 6:
                Runtime.getRuntime().halt(0);
            default:
                System.out.println("Choose option 1 or 2");
        }

    }

    private static void menuListVendor(int vID,String vendorName) {
        Menu m[] = OrderFactory.fetchMenuVendor(vID);
        System.out.format("%10s %20s %15s %10s", "Food Id", "Food Name", "Food Price", "vendor Id");
        System.out.println();
        for (int i = 0; i < m.length; i++) {
            System.out.format("%10s %20s %15d %10d", m[i].getFoodId(), m[i].getFoodName(), m[i].getFoodPrice(),
                    m[i].getVendorId());
            System.out.println();
        }
        sc.next();
        vlogin(vID, vendorName);
    }

    private static void editMenu(int vID, String vendorName) {
        System.out.println("########### vendor " + vID + " welcome to menu editing system #######");
        System.out.println("1. edit food price for existing process");
        System.out.println("2. add new food item");
        int opt = sc.nextInt();
        switch (opt) {
            case 1:
                foodPrice(vID, vendorName);
                break;
            case 2:
                addNewItem(vID, vendorName);

                break;

            default:
                break;
        }
    }

    private static void addNewItem(int vID, String vendorName) {
        System.out.println("we are happy to have new food in our list\n");
        System.out.println("enter the name of new food item :");
        // sc.next();
        String FoodName = sc.nextLine();
        FoodName = sc.nextLine();
        System.out.println("enter the food price");
        int foodprice = sc.nextInt();
        UserDB.addnewdb(FoodName, foodprice, vID);
        sc.next();
        vlogin(vID, vendorName);
    }

    private static void foodPrice(int vID, String vendorName) {
        menuList();
        System.out.println("enter the foodID ");
        int fid = sc.nextInt();
        System.out.println("enter the new price");
        int fprice = sc.nextInt();
        UserDB.FoodPriceChanger(fid, fprice);
        vlogin(vID, vendorName);

    }

    // then use update
    /**
     * if Login success
     */
    public static void cLogin(int cID, String customerName) {
        System.out.println("----------------------------");
        System.out.println("Canteen Management System");
        System.out.println("----------------------------");
        System.out.println("Customer ID : " + cID);
        System.out.println("----------------------------");
        System.out.println("welcome : " + customerName);
        System.out.println("----------------------------");
        System.out.println("Enter your choice....");
        System.out.println("1.Show Menu");
        System.out.println("2.Placing Order");
        System.out.println("3.order History");
        System.out.println("4.profile");
        System.out.println("5.Wallet Balance");
        System.out.println("6.cancel order");
        System.out.println("7.Rating order");
        System.out.println("8.Total calories consuption");
        System.out.println("9.Exit");
        // final int customerID=cID;

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                menuList();
                cLogin(cID, customerName);
                break;
            case 2:
                placeOrder(cID);
                break;
            case 3:
                CustomerOrderHistory(cID);
                break;
            case 4:
                customerProfile(cID, customerName);
                break;
            case 5:
                System.out.println("your wallet balance is : " + UserDB.customerWallet(cID));
                cLogin(cID, customerName);
                break;
            case 6:
                System.out.println("Under Development");
                cLogin(cID, customerName); // 6. cance_l order need to create
                break;
            case 7:
                System.out.println("Under Development");
                cLogin(cID, customerName); // 7. Rating order need to create little less priority
                break;
            case 8:
                System.out.println("Under Development");
                cLogin(cID, customerName); // 8. Total calories consuption need to create little less priority
                break;
            case 9:
                Runtime.getRuntime().halt(0);
            default:
                System.out.println("Choose option 1 or 2");
        }

    }

    /**
     * this method is to place food order.
     * 
     * @param customerID
     */
    public static void placeOrder(int customerID) {
        menuList();
        System.out.println("Enter the Food id");
        int fid = sc.nextInt();
        System.out.println("Enter the Food Quantity");
        int fquan = sc.nextInt();
        // acceptRejectOrder(fid,fquan,customerID);
        // Add other attributes to complete the functionality
        int r = OrderFactory.OrderFood(fid, fquan, customerID);
        System.out.println(r + "   is inserted.....");
    }

    /**
     * this method is to fetch Menu list.
     */
    public static void menuList() {
        Menu m[] = OrderFactory.fetchMenu();
        System.out.format("%10s %20s %15s %10s", "Food Id", "Food Name", "Food Price", "vendor Id");
        System.out.println();
        for (int i = 0; i < m.length; i++) {
            System.out.format("%10s %20s %15d %10d", m[i].getFoodId(), m[i].getFoodName(), m[i].getFoodPrice(),
                    m[i].getVendorId());
            System.out.println();
        }
    }

    /**
     * this method is to acceptRejectOrder.
     */
    public static String acceptRejectOrder(int vID,String vendorName) {
        Order o[] = OrderFactory.vendorOrderHistoryToday(vID,vendorName);
        System.out.format("%10s %10s %12s %10s %20s %10s %15s %30s %15s %15s %10s", "order NO", "Vendor ID",
                "customer id", "foodid", "Food Name", "quantity", "ETA", "order placed at", "total value",
                "order Status", "reason");
        System.out.println();
        for (int i = 0; i < o.length; i++) {
            System.out.format("%10s %10s %12s %10s %20s %10s %15s %30s %15s %15s %10s ", o[i].getOrderNo(),
                    o[i].getVendorId(), o[i].getCustomerId(), o[i].getFoodId(), o[i].getFoodname(),
                    o[i].getFoodQunatity(), o[i].getETA(), o[i].getOrderPlacedTime(), o[i].getValueOFTheOrder(),
                    o[i].getOrderstatus(), o[i].getReason());
            System.out.println();
        }
        System.out.println("enter the order number to accept of reject : ");
        int onum = sc.nextInt();
        System.out.println("enter A to accept and R for reject the order : ");
        String AorC = sc.next();
        String reason = null;
        if (AorC.equals("R")) {
            System.out.println("enter the reason for rejecting");
            reason = sc.next();
        }

        OrderDb.classupdateOrder(onum, AorC, reason);
        sc.next();
        vlogin(vID, "back");
        return AorC;
    }

    /**
     * this method is for customerProfile.
     */
    public static void customerProfile(int cID, String customerName) {
        Customer c[] = UserFactory.fetchCustomerProfile(cID, customerName);
        System.out.format("%10s %10s %15s %15s %10s", "vendor id", "vendor Name", "vendor phone", "vendor specality",
                "vendor password");
        System.out.println();
        for (int i = 0; i < c.length; i++) {
            System.out.format("%10s %10s %15s %15s %10s", c[i].getCustomerId(), c[i].getCustomerName(),
                    c[i].getCustomerPhone(), c[i].getCustomerEmail(), c[i].getCustomerWalletBal(), c[i].getPassword());
            System.out.println("\npress enter something to go back to previous menu");
            sc.next();
            cLogin(cID, customerName);
        }
    }

    /**
     * this method is for VendorProfile.
     */
    public static void vendorProfile(int vID, String vendorName) {
        Vendor v[] = UserFactory.fetchVendorProfile();
        System.out.format("%10s %10s %15s %15s %10s", "vendor id", "vendor Name", "vendor phone", "vendor specality",
                "vendor password");
        System.out.println();
        for (int i = 0; i < v.length; i++) {
            System.out.format("%10s %10s %15s %15s %10s", v[i].getVendorId(), v[i].getVendorName(),
                    v[i].getVendorPhone(), v[i].getVendorSpecs(), v[i].getPassword());
            System.out.println("\npress enter something to go back to previous menu");
            sc.next();
            vlogin(vID, vendorName);
        }
    }

    /**
     * this method is for VendorOderHistory.
     */
    public static void VendorOrderHistory(int vID) {
        Order o[] = OrderFactory.vendorOrderHistory(vID);
        System.out.format("%10s %10s %12s %10s %20s %10s %15s %30s %15s %15s %10s", "order NO", "Vendor ID",
                "customer id", "foodid", "Food Name", "quantity", "ETA", "order placed at", "total value",
                "order Status", "reason");
        System.out.println();
        for (int i = 0; i < o.length; i++) {
            System.out.format("%10s %10s %12s %10s %20s %10s %15s %30s %15s %15s %10s ", o[i].getOrderNo(),
                    o[i].getVendorId(), o[i].getCustomerId(), o[i].getFoodId(), o[i].getFoodname(),
                    o[i].getFoodQunatity(), o[i].getETA(), o[i].getOrderPlacedTime(), o[i].getValueOFTheOrder(),
                    o[i].getOrderstatus(), o[i].getReason());
            System.out.println();
        }
        String vendorname = "back";
        vlogin(vID, vendorname);
    }

    /**
     * this method is for CustomerOrderHistory.
     */
    public static void CustomerOrderHistory(int cID) {
        String customerName = "back";
        Order o[] = OrderFactory.customerOrderHistory(cID);
        System.out.format("%10s %10s %12s %10s %20s %10s %15s %30s %15s %15s %10s", "order NO", "Vendor ID",
                "customer id", "foodid", "Food Name", "quantity", "ETA", "order placed at", "total value",
                "order Status", "reason");
        System.out.println();
        for (int i = 0; i < o.length; i++) {
            System.out.format("%10s %10s %12s %10s %20s %10s %15s %30s %15s %15s %10s ", o[i].getOrderNo(),
                    o[i].getVendorId(), o[i].getCustomerId(), o[i].getFoodId(), o[i].getFoodname(),
                    o[i].getFoodQunatity(), o[i].getETA(), o[i].getOrderPlacedTime(), o[i].getValueOFTheOrder(),
                    o[i].getOrderstatus(), o[i].getReason());
            System.out.println();

        }
        System.out.println("enter something to go back to customr login");
        sc.next();

        cLogin(cID, customerName);

    }

}
