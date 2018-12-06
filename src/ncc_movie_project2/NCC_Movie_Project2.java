/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncc_movie_project2;

/**
 *
 * @author Dao Viet Anh
 */
public class NCC_Movie_Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (Utils.Funcs.testConn() != "") {
            formLichChieu formLichChieu1 = new formLichChieu();
            formLichChieu1.setVisible(true);
        }

    }
    
}
