/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingve1.Nam;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AdMins
 */
public class TenPhim1 {
    public static List<TenPhim> getAll() throws SQLException {
        List<TenPhim> lstPhim = new ArrayList<TenPhim>();
        Connection con = Database.getConnecttion();
        Statement stmt = con.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from phim");
        while(rs.next()) {
            TenPhim phim = new TenPhim();
            phim.setId(rs.getInt(1));
            phim.setTenPhim(rs.getString(2));
            lstPhim.add(phim);
        }
        con.close();  
        return lstPhim;
    }
    
    public static void main(String args[]) throws SQLException{ 
        System.out.println(getAll());
    }
}
