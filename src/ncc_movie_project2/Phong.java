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
public class Phong {
    String maPhongChieu ;
    String mayChieu ;
    String soGhe;
    String amThanh;
    String dienTich;
    String chuThich;

    public Phong() {
    }

    public Phong(String maPhongChieu, String soGhe, String mayChieu, String amThanh, String dienTich, String chuThich) {
        this.maPhongChieu = maPhongChieu;
        this.soGhe = soGhe;
        this.mayChieu = mayChieu;
        this.amThanh = amThanh;
        this.dienTich = dienTich;
        this.chuThich = chuThich;
    }

    public String getMaPhongChieu() {
        return maPhongChieu;
    }

    public void setMaPhongChieu(String maPhongChieu) {
        this.maPhongChieu = maPhongChieu;
    }

    public String getMayChieu() {
        return mayChieu;
    }

    public void setMayChieu(String mayChieu) {
        this.mayChieu = mayChieu;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public String getAmThanh() {
        return amThanh;
    }

    public void setAmThanh(String amThanh) {
        this.amThanh = amThanh;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }
    
    
}
