/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncc_movie_project2;

import static MD5.md5.MD5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hoang Kim Chuc
 */
public class formDangKy extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement ps = null;
    
    public formDangKy() {
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_taoTenDangNhap = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_taoMatKhau = new javax.swing.JPasswordField();
        txt_nhapLaiMatKhau = new javax.swing.JPasswordField();
        btn_taoNguoiDung = new javax.swing.JButton();
        btn_huyTao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Đăng Ký ");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ncc_movie_project2/res/img/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setText("Tên đăng nhập : ");

        jLabel3.setText("Mật khẩu : ");

        jLabel4.setText("Nhập lại mật khẩu : ");

        btn_taoNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        btn_taoNguoiDung.setText("Tạo");
        btn_taoNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoNguoiDungActionPerformed(evt);
            }
        });

        btn_huyTao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        btn_huyTao.setText("Hủy");
        btn_huyTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyTaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_taoNguoiDung)
                        .addGap(18, 18, 18)
                        .addComponent(btn_huyTao))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(38, 38, 38))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(66, 66, 66)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_taoTenDangNhap)
                            .addComponent(txt_taoMatKhau)
                            .addComponent(txt_nhapLaiMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_taoTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_taoMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_nhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_taoNguoiDung)
                    .addComponent(btn_huyTao))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_huyTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyTaoActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.parent.setVisible(true);
    }//GEN-LAST:event_btn_huyTaoActionPerformed
   
    formDangNhap parent;
    int eu;
    private void btn_taoNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoNguoiDungActionPerformed
        soSanhMatKhau();
        String tenDangNhap = txt_taoTenDangNhap.getText().toString();
        String nhapLaiMatKhau = txt_nhapLaiMatKhau.getText().toString();
      
        nhapLaiMatKhau = MD5(nhapLaiMatKhau);
        nhapLaiMatKhau = nhapLaiMatKhau + "chucanh";
        nhapLaiMatKhau = MD5(nhapLaiMatKhau);
        
        if(soSanhMatKhau()) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "");
                String sql = "insert into dangnhap(tenDangNhap, matKhau) values ('"+tenDangNhap+"', '"+nhapLaiMatKhau+"')";
                ps = conn.prepareStatement(sql);
                eu = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(formDangKy.class.getName()).log(Level.SEVERE, null, ex);
                eu = -1;
            }
        }
        
        if(eu <= 0) {
            JOptionPane.showMessageDialog(this, "Đăng ký thất bại !", "THẤT BẠI ", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công !", "THÀNH CÔNG ", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_taoNguoiDungActionPerformed
    
    private boolean soSanhMatKhau() {
        boolean ketqua = false;
        String matKhau = txt_taoMatKhau.getText().toString();
        String nhapLaiMatKhau = txt_nhapLaiMatKhau.getText().toString();
        
        matKhau = MD5(matKhau);
        matKhau =matKhau+ "chucanh";
        matKhau = MD5(matKhau);
        
        nhapLaiMatKhau = MD5(nhapLaiMatKhau);
        nhapLaiMatKhau = nhapLaiMatKhau +"chucanh";
        nhapLaiMatKhau = MD5(nhapLaiMatKhau);
        if(nhapLaiMatKhau.equals(matKhau)) {
            ketqua = true;
        }
        else {
            ketqua = false;
        }
        return ketqua;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formDangKy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_huyTao;
    private javax.swing.JButton btn_taoNguoiDung;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txt_nhapLaiMatKhau;
    private javax.swing.JPasswordField txt_taoMatKhau;
    private javax.swing.JTextField txt_taoTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
