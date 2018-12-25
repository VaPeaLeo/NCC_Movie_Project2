/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncc_movie_project2;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dao Viet Anh
 */
public class formPhong extends javax.swing.JFrame {

    List<Phong> danhSachPhong = new ArrayList<>();

    DefaultTableModel tableModel;

    Connection conn = null;
    PreparedStatement preparedStatement = null;

    public formPhong() {

        initComponents();

        tableModel = (DefaultTableModel) tblPhong.getModel();

        loadData();
        showData();

        tab_all.remove(tabUpdate);
    }

    private void loadData() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "");
            String sql = "select * from phongchieu";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            danhSachPhong.clear();
            while (rs.next()) {
                Phong phong = new Phong(rs.getString("maPhongChieu"),
                        rs.getString("soGhe"),
                        rs.getString("mayChieu"),
                        rs.getString("amThanh"),
                        rs.getString("dienTich"),
                        rs.getString("chuThich"));
                danhSachPhong.add(phong);

            }
        } catch (Exception e) {
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(formPhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(formPhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void showData() {
        tableModel.setRowCount(0);
        for (int i = 0; i < danhSachPhong.size(); i++) {
            tableModel.addRow(new String[]{danhSachPhong.get(i).maPhongChieu,
                danhSachPhong.get(i).soGhe,
                danhSachPhong.get(i).mayChieu,
                danhSachPhong.get(i).amThanh,
                danhSachPhong.get(i).dienTich,
                danhSachPhong.get(i).chuThich});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tab_all = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_maPhongChieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_soGhe = new javax.swing.JTextField();
        txt_mayChieu = new javax.swing.JTextField();
        txt_amThanh = new javax.swing.JTextField();
        txt_dienTich = new javax.swing.JTextField();
        txt_chuThich = new javax.swing.JTextField();
        btn_Create = new javax.swing.JButton();
        btn_Cancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhong = new javax.swing.JTable();
        btn_Edit = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        tabUpdate = new javax.swing.JPanel();
        tab_update = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_maPhongChieu1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_soGhe1 = new javax.swing.JTextField();
        txt_mayChieu1 = new javax.swing.JTextField();
        txt_amThanh1 = new javax.swing.JTextField();
        txt_dienTich1 = new javax.swing.JTextField();
        txt_chuThich1 = new javax.swing.JTextField();
        btn_Save = new javax.swing.JButton();
        btn_CancelUpdate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lí phòng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jLabel2.setText("Mã Phòng Chiếu : ");

        jLabel3.setText("Số Ghế : ");

        jLabel4.setText("Âm Thanh : ");

        jLabel5.setText("Diện Tích : ");

        jLabel6.setText("Máy Chiếu : ");

        jLabel7.setText("Chú Thích : ");

        btn_Create.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/plus.png"))); // NOI18N
        btn_Create.setText("Tạo");
        btn_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateActionPerformed(evt);
            }
        });

        btn_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        btn_Cancel.setText("Hủy");
        btn_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(txt_maPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_soGhe)
                            .addComponent(txt_mayChieu)
                            .addComponent(txt_amThanh)
                            .addComponent(txt_dienTich)
                            .addComponent(txt_chuThich, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))))
                .addGap(41, 41, 41))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Create)
                .addGap(30, 30, 30)
                .addComponent(btn_Cancel)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_maPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_soGhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_mayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_amThanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dienTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_chuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Create)
                    .addComponent(btn_Cancel))
                .addGap(30, 30, 30))
        );

        tab_all.addTab("Tạo Phòng Chiếu", jPanel2);

        tblPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phòng Chiếu", "Số Ghế", "Máy Chiếu ", "Âm Thanh", "Diện Tích", "Chú Thích"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhong);

        btn_Edit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pencil-edit-button.png"))); // NOI18N
        btn_Edit.setText("Sửa");
        btn_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditActionPerformed(evt);
            }
        });

        btn_Delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btn_Delete.setText("Xóa");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(22, 232, Short.MAX_VALUE)
                .addComponent(btn_Edit)
                .addGap(39, 39, 39)
                .addComponent(btn_Delete)
                .addGap(37, 37, 37))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Edit)
                    .addComponent(btn_Delete))
                .addGap(23, 23, 23))
        );

        tab_all.addTab("Danh Sách Phòng Chiếu", jPanel4);

        jLabel8.setText("Mã Phòng Chiếu : ");

        jLabel9.setText("Số Ghế : ");

        jLabel10.setText("Âm Thanh : ");

        jLabel11.setText("Diện Tích : ");

        jLabel12.setText("Máy Chiếu : ");

        jLabel13.setText("Chú Thích : ");

        btn_Save.setBackground(new java.awt.Color(204, 255, 204));
        btn_Save.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/download-button (1).png"))); // NOI18N
        btn_Save.setText("Lưu");
        btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SaveActionPerformed(evt);
            }
        });

        btn_CancelUpdate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_CancelUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        btn_CancelUpdate.setText("Hủy");
        btn_CancelUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab_updateLayout = new javax.swing.GroupLayout(tab_update);
        tab_update.setLayout(tab_updateLayout);
        tab_updateLayout.setHorizontalGroup(
            tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_updateLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab_updateLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(txt_maPhongChieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab_updateLayout.createSequentialGroup()
                        .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_soGhe1)
                            .addComponent(txt_mayChieu1)
                            .addComponent(txt_amThanh1)
                            .addComponent(txt_dienTich1)
                            .addComponent(txt_chuThich1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))))
                .addGap(41, 41, 41))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_updateLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Save)
                .addGap(31, 31, 31)
                .addComponent(btn_CancelUpdate)
                .addGap(63, 63, 63))
        );
        tab_updateLayout.setVerticalGroup(
            tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_updateLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_maPhongChieu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_soGhe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_mayChieu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_amThanh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dienTich1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_chuThich1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(tab_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Save)
                    .addComponent(btn_CancelUpdate))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout tabUpdateLayout = new javax.swing.GroupLayout(tabUpdate);
        tabUpdate.setLayout(tabUpdateLayout);
        tabUpdateLayout.setHorizontalGroup(
            tabUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
            .addGroup(tabUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabUpdateLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tab_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        tabUpdateLayout.setVerticalGroup(
            tabUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
            .addGroup(tabUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabUpdateLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tab_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tab_all.addTab("Sửa Thông Tin Phòng", tabUpdate);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Quản Lý Phòng Chiếu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tab_all, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab_all)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelActionPerformed
        txt_maPhongChieu.setText("");
        txt_amThanh.setText("");
        txt_dienTich.setText("");
        txt_mayChieu.setText("");
        txt_soGhe.setText("");
        txt_chuThich.setText("");
    }//GEN-LAST:event_btn_CancelActionPerformed
    int eu;
    private void btn_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateActionPerformed
        // TODO, add your handling code here:
        String maPhongChieu = txt_maPhongChieu.getText().toString();
        String soGhe = txt_soGhe.getText().toString();
        String mayChieu = txt_mayChieu.getText().toString();
        String amThanh = txt_amThanh.getText().toString();
        String dienTich = txt_dienTich.getText().toString();
        String chuThich = txt_chuThich.getText().toString();

        Phong phong = new Phong(maPhongChieu, soGhe, mayChieu, amThanh, dienTich, chuThich);
        danhSachPhong.add(phong);

        tableModel.addRow(new String[]{maPhongChieu, soGhe, mayChieu, amThanh, dienTich, chuThich});
        insertIntoDatabase(phong);

        if (eu >= 0) {
            JOptionPane.showMessageDialog(this, "Tạo phòng chiếu thành công", "SUCCESS ", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Tạo phòng chiếu thất bại", "FAIL ", JOptionPane.ERROR_MESSAGE);

        }

        loadData();
        showData();
    }//GEN-LAST:event_btn_CreateActionPerformed

    private void insertIntoDatabase(Phong phong) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "");
            String sql = "insert into phongchieu(maPhongChieu, soGhe, mayChieu,amThanh,dienTich,chuThich) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, phong.maPhongChieu);
            preparedStatement.setString(2, phong.soGhe);
            preparedStatement.setString(3, phong.mayChieu);
            preparedStatement.setString(4, phong.amThanh);
            preparedStatement.setString(5, phong.dienTich);
            preparedStatement.setString(6, phong.chuThich);

//            preparedStatement.execute();
            eu = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            eu = -1;
        } finally {
            try {
                preparedStatement.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        // TODO add your handling code here:

        int selectedRowIndex = tblPhong.getSelectedRow();
        String id = (tableModel.getValueAt(selectedRowIndex, 0).toString());
        int ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa phòng chiếu này?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "");
            String sql = "delete from phongchieu where maPhongChieu= '" + id + "'";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            ret = preparedStatement.executeUpdate();
            if (ret != -1) {
                JOptionPane.showMessageDialog(this, "Phòng chiếu này đã được xóa");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadData();
        showData();

    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void tblPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_tblPhongMouseClicked

    void Save(String old_id, Phong updatedphong) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "");
            String sql = "update phongchieu set maPhongChieu= '" + updatedphong.maPhongChieu + "',"
                    + " soGhe= '" + updatedphong.soGhe + "', "
                    + " mayChieu= '" + updatedphong.mayChieu + "',"
                    + " amThanh='" + updatedphong.amThanh + "', "
                    + " dienTich='" + updatedphong.dienTich + "',"
                    + " chuThich='" + updatedphong.chuThich + "' where maPhongChieu= '" + old_id + "'";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    private void btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SaveActionPerformed
        // TODO add your handling code here:
        Phong updatedphong = new Phong();
        updatedphong.maPhongChieu = txt_maPhongChieu1.getText().toString();
        updatedphong.soGhe = txt_soGhe1.getText().toString();
        updatedphong.mayChieu = txt_mayChieu1.getText().toString();
        updatedphong.amThanh = txt_amThanh1.getText().toString();
        updatedphong.dienTich = txt_dienTich1.getText().toString();
        updatedphong.chuThich = txt_chuThich1.getText().toString();
        Save(old_id, updatedphong);

//        JOptionPane optionPane = new JOptionPane("Sửa phòng chiếu thành công!",JOptionPane.INFORMATION_MESSAGE);
//        JDialog dialog = optionPane.createDialog("SUCCESS!");
//        dialog.setAlwaysOnTop(true);
//        dialog.setVisible(true);
        JOptionPane.showMessageDialog(this, "Sửa phòng chiếu thành công!", "SUCCESS ", JOptionPane.INFORMATION_MESSAGE);

        tab_all.remove(tabUpdate);
        loadData();
        showData();

    }//GEN-LAST:event_btn_SaveActionPerformed
    String old_id;
    private void btn_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditActionPerformed
        // TODO add your handling code here:
        tabUpdate.setName("Sửa thông tin phòng");
        tab_all.add(tabUpdate);
        tab_all.setSelectedComponent(tabUpdate);

        int selectedRowIndex = tblPhong.getSelectedRow();
        old_id = tableModel.getValueAt(selectedRowIndex, 0).toString();
        txt_maPhongChieu1.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
        txt_soGhe1.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
        txt_mayChieu1.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
        txt_amThanh1.setText(tableModel.getValueAt(selectedRowIndex, 3).toString());
        txt_dienTich1.setText(tableModel.getValueAt(selectedRowIndex, 4).toString());
        txt_chuThich1.setText(tableModel.getValueAt(selectedRowIndex, 5).toString());
    }//GEN-LAST:event_btn_EditActionPerformed

    private void btn_CancelUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelUpdateActionPerformed
        // TODO add your handling code here:
        tab_all.remove(tabUpdate);
    }//GEN-LAST:event_btn_CancelUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(formPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JButton btn_CancelUpdate;
    private javax.swing.JButton btn_Create;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_Save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel tabUpdate;
    private javax.swing.JTabbedPane tab_all;
    private javax.swing.JPanel tab_update;
    private javax.swing.JTable tblPhong;
    private javax.swing.JTextField txt_amThanh;
    private javax.swing.JTextField txt_amThanh1;
    private javax.swing.JTextField txt_chuThich;
    private javax.swing.JTextField txt_chuThich1;
    private javax.swing.JTextField txt_dienTich;
    private javax.swing.JTextField txt_dienTich1;
    private javax.swing.JTextField txt_maPhongChieu;
    private javax.swing.JTextField txt_maPhongChieu1;
    private javax.swing.JTextField txt_mayChieu;
    private javax.swing.JTextField txt_mayChieu1;
    private javax.swing.JTextField txt_soGhe;
    private javax.swing.JTextField txt_soGhe1;
    // End of variables declaration//GEN-END:variables
}
