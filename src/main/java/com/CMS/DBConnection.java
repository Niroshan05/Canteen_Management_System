package com.CMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getDBConnection() {
        Connection conn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root", "<ENTER_YOUR_ROOT_PASSWORD>");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {

            e.printStackTrace();

        }
        return conn;
          
    }
}
