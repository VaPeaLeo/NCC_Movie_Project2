/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncc_movie_project2;

import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.SQLException;

/**
 *
 * @author Dao Viet Anh
 */
public class NCC_Movie_Project2 {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        if (!"".equals(Utils.Funcs.testConn())) {
            formDangNhap  formDangNhap1 = new formDangNhap();
            formDangNhap1.setVisible(true);
            
            // formLichChieu formLichChieu1 = new formLichChieu();
            //formLichChieu1.setVisible(true);
        } else {
        }

    }
  


}
