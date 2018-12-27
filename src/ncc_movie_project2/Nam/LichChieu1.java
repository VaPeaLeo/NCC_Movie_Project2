/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingve1.Nam;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AdMins
 */
public class LichChieu1 {
    
    public static List<LichChieu> getLichChieuById(int movieID) throws SQLException {
        List<LichChieu> lstLichChieu = new ArrayList<LichChieu>();
        Connection con = Database.getConnecttion();
        Statement stmt = con.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from lichchieu where movieID=" + movieID);
        String gioBatDauChieu = "";
        while(rs.next()) {
            LichChieu lichChieu = new LichChieu();
            lichChieu.setId(rs.getInt(1));
            lichChieu.setRoom(rs.getString(3));
            gioBatDauChieu = convertTime(rs.getLong(4));
            lichChieu.setGioBatDauChieu(gioBatDauChieu);
            lstLichChieu.add(lichChieu);
        }
        con.close();  
        return lstLichChieu;
    }
    
    public static String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return format.format(date);
    }
}
