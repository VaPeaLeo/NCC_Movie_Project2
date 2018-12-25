/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncc_movie_project2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Dao Viet Anh
 */
public class Utils {

    static class Funcs {

        static String testConn() throws SQLException {
            String result = "";
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");
                String sql = "select * from lichChieu";
                Statement stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);

                if (rs.next()) {
                    result = (rs.getInt(1) + "");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();

                }
                return result;
            }
        }

        static String getConnString() {
            return "jdbc:mysql://localhost:3306/project2";
        }

        static Boolean checkInfoDangNhap(String tenDangNhap, String matKhau) throws SQLException {
            Boolean result = false;
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");
                String sql = "select * from dangNhap where tenDangNhap = '" + tenDangNhap + "'"
                        + " and matKhau = '" + matKhau + "' ";
                Statement stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);

                if (rs.first()) {
                    if (rs.getString(1) != "") {
                        result = true;
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();

                }
                return result;
            }
        }

        static DefaultTableModel modelBangLichChieu(DefaultTableModel modelToFill, long startTime, long endTime) throws SQLException {
            modelToFill.setRowCount(0);
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");
                String endLimit = (endTime != 0 ? " and gioBatDauChieu <= " + endTime : "");
                String sql
                        = "select id, tenPhim, room, gioBatDauChieu from lichChieu, phim where gioBatDauChieu >=" + startTime + ""
                        + endLimit + " and movieID = phimID order by gioBatDauChieu";

                Statement stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);

                if (rs.first()) {
                    if (rs.getInt(1) != -1) {
                        //check
                        System.out.println(rs.getLong(4));

                        do {
                            Object[] row = new Object[4];
                            row[0] = rs.getInt(1);
                            row[1] = rs.getString(2);
                            row[2] = rs.getString(3);
                            row[3] = timeMilliToString(rs.getLong(4));
                            modelToFill.addRow(row);
                        } while (rs.next());
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();
                }
//                System.out.println(modelToFill.getValueAt(0, 3).toString());
                return modelToFill;
            }
        }

        static ArrayList<String> layDSPhim(String tinhTrang) throws SQLException {
            ArrayList<String> result = new ArrayList();
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");

                String sql
                        = "select phimID, tenPhim from phim where tinhTrang =" + tinhTrang;
                System.out.println(sql);
                Statement stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);

//    System.out.println(rs.toString());
                System.out.println("before check");
                while (rs.next()) {
//                    System.out.println("test : " + rs.getString("tenPhim"));
                    result.add(rs.getString("tenPhim") + " (" + rs.getString("phimID") + ")");

                }
//                if (rs.first()) {
//               
//                   System.out.println(rs.toString());
//                        do {                        
//                            result.add(rs.getString(1));
//                         
//                        } while (rs.next());
//                    
//                }

            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();
                }
                System.out.println("stop");
                return result;
            }
        }

        static ArrayList<String> layDSPhongDaDung(Long starttime, Long endtime) throws SQLException {
            ArrayList<String> result = new ArrayList();
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");

                String sql
                        = "select room, thoiLuongPhut  from lichChieu, phim where "
                        + "movieID = phimID and (gioBatDauChieu <=" + endtime
                        + " and (gioBatDauChieu+ thoiLuongPhut*60*1000) >= " + endtime
                        + ") or (gioBatDauChieu <=" + starttime
                        + " and (gioBatDauChieu+ thoiLuongPhut*60*1000) >= " + starttime + ") ";
//                System.out.println(sql);
                Statement stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);

//    System.out.println(rs.toString());
//                System.out.println("before check");
                while (rs.next()) {
//                    System.out.println("test : " + rs.getString("tenPhim"));
                    if (result.contains(rs.getString("room"))==false) {
                        result.add(rs.getString("room"));

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();
                }
                System.out.println("stop");
                return result;
            }
        }

        static ArrayList<String> layDSPhongOK(Long starttime, Long endtime) throws SQLException {
            ArrayList<String> result = new ArrayList();
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");

                String sql = "";
                if (layDSPhongDaDung(starttime, endtime).size() < 1) {
                    sql = "select maPhongChieu  from phongchieu";
                } else {
                    String phongDaDungList = "(";
                    for (String phongDaDung : layDSPhongDaDung(starttime, endtime)) {
                        phongDaDungList += "'" + phongDaDung + "',";
                    }
                    phongDaDungList += ")";
                    phongDaDungList = phongDaDungList.replace(",)", ")");
                    System.out.println("phongdadung =" + phongDaDungList);
                    sql = "select maPhongChieu  from phongchieu where maPhongChieu not in " + phongDaDungList;
                }
//                System.out.println(sql);
                Statement stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);

//    System.out.println(rs.toString());
//                System.out.println("before check");
                while (rs.next()) {
//                    System.out.println("test : " + rs.getString("tenPhim"));
                    result.add(rs.getString(1));

                }

            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();
                }
//                System.out.println("stop");
                return result;
            }

        }

        static Long layThoiLuongPhimMillis(int phimID) throws SQLException {
            Long result = 0L;
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");

                String sql
                        = "select thoiLuongPhut  from phim where phimID =" + phimID;
//                System.out.println(sql);
                Statement stmnt = conn.createStatement();
                ResultSet rs = stmnt.executeQuery(sql);

//    System.out.println(rs.toString());
//                System.out.println("before check");
                while (rs.next()) {
//                    System.out.println("test : " + rs.getString("tenPhim"));
                    result = rs.getInt("thoiLuongPhut") * 60 * 1000L;

                }

            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();
                }
//                System.out.println("stop");
                return result;
            }

        }
        
        static boolean themLichChieu(int movieID,String room, Long gioBatDauChieu) throws SQLException {
            boolean result = false;
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");

                String sql
                        = "insert into lichChieu (movieID, room, gioBatDauChieu) values"
                        + "("+movieID+", '"+room+"',"+gioBatDauChieu+")";
                System.out.println(sql);
                Statement stmnt = conn.createStatement();
                int rows = stmnt.executeUpdate(sql);

//    System.out.println(rs.toString());
//                System.out.println("before check");
                if(rows>0) result = true;
            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();
                }
//                System.out.println("stop");
                return result;
            }

        }
        
        static boolean xoaLichChieu(ArrayList<Integer> idList ) throws SQLException {
            boolean result = false;
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");

                String idListStr = "(";
                    for (int id : idList) {
                        idListStr += "'" + id + "',";
                    }
                    idListStr += ")";
                    idListStr = idListStr.replace(",)", ")");
                    System.out.println("id List  =" + idListStr);
                String sql
                        = "delete from lichChieu where id in "+ idListStr;
//                System.out.println(sql);
                Statement stmnt = conn.createStatement();
                int rows = stmnt.executeUpdate(sql);

//    System.out.println(rs.toString());
//                System.out.println("before check");
                if(rows>0) result = true;
            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();
                }
//                System.out.println("stop");
                return result;
            }

        }
        
        static boolean updateLichChieu(int id, int phimID, String phong, Long gioChieu ) throws SQLException {
            boolean result = false;
            String url = getConnString();
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "root", "");

                String sql
                        = "update lichChieu"
                        + " set movieID = "+phimID
                        + ", room = '"+phong+"'"
                        + ", gioBatDauChieu= "+gioChieu
                        + " where id = "+id;
                System.out.println(sql);
                Statement stmnt = conn.createStatement();
                int rows = stmnt.executeUpdate(sql);

//    System.out.println(rs.toString());
//                System.out.println("before check");
                if(rows>0) result = true;
            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (conn != null) {
                    conn.close();
                }
//                System.out.println("stop");
                return result;
            }

        }

        static String datePickerToVNeseString(LocalDate date) {

            return "ngày " + date.getDayOfMonth() + " tháng " + date.getMonthValue()
                    + " năm " + date.getYear();
        }

        static long datePickerToTimeMilli(LocalDate date) {

            return date.atStartOfDay().toInstant(ZoneOffset.ofHours(7)).toEpochMilli();
        }
        
        static String timeMilliToString(Long time){
            String result = "";
         result=   SimpleDateFormat.getDateTimeInstance().format(new Date(time));
            return result;
        }
    }
}
