/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingve1.Nam;

import java.sql.*;  

/**
 *
 * @author AdMins
 */
public class Database {
    private static final String USER_DB = "root";
    private static final String PASS_DB = "";
    public static Connection getConnecttion() {
       try{  
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/project2",USER_DB, PASS_DB);  
        return con;
        }catch(Exception e){ 
            System.out.println(e);
        }
       return null;
    }
    
    public static void main(String args[]){ 
        System.out.println(getConnecttion());
    }
}
