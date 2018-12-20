/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncc_movie_project2;

/**
 *
 * @author Hoang Kim Chuc
 */
public class Phim {
    String phimID;
    String tenPhim;
    String thoiLuongPhut;
    String namCongChieu;
    String quocGia;
    String tinhTrang;

    public Phim() {
    }

    public Phim(String phimID, String tenPhim, String thoiLuongPhut, String namCongChieu, String quocGia, String tinhTrang) {
        this.phimID = phimID;
        this.tenPhim = tenPhim;
        this.thoiLuongPhut = thoiLuongPhut;
        this.namCongChieu = namCongChieu;
        this.quocGia = quocGia;
        this.tinhTrang = tinhTrang;
    }

    public String getPhimID() {
        return phimID;
    }

    public void setPhimID(String phimID) {
        this.phimID = phimID;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getThoiLuongPhut() {
        return thoiLuongPhut;
    }

    public void setThoiLuongPhut(String thoiLuongPhut) {
        this.thoiLuongPhut = thoiLuongPhut;
    }

    public String getNamCongChieu() {
        return namCongChieu;
    }

    public void setNamCongChieu(String namCongChieu) {
        this.namCongChieu = namCongChieu;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    
}
