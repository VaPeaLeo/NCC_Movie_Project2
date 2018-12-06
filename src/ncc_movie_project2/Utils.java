/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncc_movie_project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dao Viet Anh
 */
public class Utils {
    static  class Funcs{
        
        static  String testConn(){
           String result = "";
           String url = "jdbc:mysql://localhost:3306/project2";
            try {
                Connection conn = DriverManager.getConnection(url,"root","");
                String sql = "select * from lichChieu";
                Statement  stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);
                
                if(rs.next()) result =  (rs.getInt(1)+"");
                
                        
            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                return result;
            }
        }
    }
}
