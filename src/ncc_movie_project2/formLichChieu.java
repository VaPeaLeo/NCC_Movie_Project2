/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncc_movie_project2;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Dao Viet Anh
 */
public class formLichChieu extends javax.swing.JFrame {

    /**
     * Creates new form formLichChieu
     */
    public formLichChieu() {
        initComponents();

        jTabbedPane1.remove(jPanel5);

        jTable1.setFont(new Font("Serif", Font.BOLD, 24));
        jTable3.setFont(new Font("Serif", Font.BOLD, 24));

        jTable1.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        jTable3.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));

        jTabbedPane1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
        jTabbedPane2.setFont(new Font("Dialog", Font.PLAIN, 20));

        datePicker1.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dce) {
                updatejPanelNoiDungXemNgay();
            }
        });
        
        
        ButtonGroup group1 = new ButtonGroup();
        group1.add(jRadioButton1);
        group1.add(jRadioButton2);

        ButtonGroup group2 = new ButtonGroup();
        group2.add(jRadioButton3);
        group2.add(jRadioButton4);
        
        datePicker2.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dce) {
            jRadioButton1.setSelected(true);
            }
        });
        datePicker3.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dce) {
            jRadioButton3.setSelected(true);
            }
        });

        

        setupTabThemSua(jComboBox1, dateTimePicker1, jComboBox2);
        
        
        datePicker2.setDateToToday();
        jRadioButton1.setSelected(true);
        updatejPanel4();
        

    }

    void setupTabThemSua(JComboBox phim, DateTimePicker dtp, JComboBox phongChieu) {
        dtp.addDateTimeChangeListener(new DateTimeChangeListener() {
            @Override
            public void dateOrTimeChanged(DateTimeChangeEvent dtce) {
                try {

                    Long starttime = getTimeMillifromDateTimePicker(dtp);
                    if (starttime == -1) {
                        return;
                    }
                    String phimDaChon = phim.getSelectedItem().toString();
                    int phimID = layPhimID(phimDaChon);
                    Long endtime = Utils.Funcs.layThoiLuongPhimMillis(phimID);
                    updateComboPhongChieu(starttime, endtime, phongChieu);
                } catch (SQLException ex) {
                    Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        if (layDSPhimDangChieu() != null) {
            phim.setModel(new DefaultComboBoxModel(layDSPhimDangChieu().toArray()));

        }
    }

    //return -1 if either date picker or time picker is null
    Long getTimeMillifromDateTimePicker(DateTimePicker dateTimePicker) {
        if (dateTimePicker.datePicker.getDate() == null
                || dateTimePicker.timePicker.getTime() == null) {
            return -1L;
        }
        LocalDateTime ldt = LocalDateTime.of(
                dateTimePicker.datePicker.getDate(),
                dateTimePicker.timePicker.getTime());
        return ldt.toEpochSecond(ZoneOffset.ofHours(7)) * 1000;
    }

    int layPhimID(String phimDaChon) {
        return Integer.parseInt(
                phimDaChon.substring(
                        phimDaChon.lastIndexOf("(") + 1, phimDaChon.lastIndexOf(")"))
        );
    }

    void updateComboPhongChieu(Long starttime, Long endtime, JComboBox phongChieu) {
        try {
            phongChieu.setModel(new DefaultComboBoxModel(Utils.Funcs.layDSPhongOK(starttime, endtime).toArray()));
//        jComboBox2.setModel(new DefaultComboBoxModel(new String[]{"sdsd","sfsd"}));
        } catch (SQLException ex) {
            Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ArrayList<String> layDSPhimDangChieu() {
        ArrayList<String> result = null;
        try {
            result = Utils.Funcs.layDSPhim("2");
        } catch (SQLException ex) {
            Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    void updatejPanelNoiDungXemNgay() {
        LocalDate date = datePicker1.getDate();
        if (date == null) {
            return;
        }
        jLabel2.setText("Đang xem " + Utils.Funcs.datePickerToVNeseString(date));
        long startTime = Utils.Funcs.datePickerToTimeMilli(datePicker1.getDate());
        long endTime = startTime + 86400 * 1000;
        try {
//         test:   DefaultTableModel model = Utils.Funcs.modelBangLichChieu(((DefaultTableModel) jTable1.getModel()), 1544261214450L, 1544261214550L);
            DefaultTableModel model = Utils.Funcs.modelBangLichChieu(((DefaultTableModel) jTable1.getModel()), startTime, endTime);
            jTable1.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        datePicker2 = new com.github.lgooddatepicker.components.DatePicker();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        datePicker3 = new com.github.lgooddatepicker.components.DatePicker();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btn_xoa_2 = new javax.swing.JButton();
        btn_sua_2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jPanelNoiDungXemNgay = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btn_sua_1 = new javax.swing.JButton();
        btn_xoa_1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        dateTimePicker2 = new com.github.lgooddatepicker.components.DateTimePicker();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý lịch chiếu");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Bắt đầu từ: ");
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));

        datePicker2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        datePicker2.setForeground(new java.awt.Color(0, 0, 204));

        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("không giới hạn");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));

        jLabel5.setText("đến  ");
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));

        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("không giới hạn");

        datePicker3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        datePicker3.setForeground(new java.awt.Color(0, 0, 204));

        jLabel6.setText("  ");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 255));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Phim", "Phòng chiếu", "Giờ chiếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(255, 255, 255));
        jTable3.setRowHeight(30);
        jScrollPane3.setViewportView(jTable3);

        jButton1.setText("Hiển thị");
        jButton1.setBackground(new java.awt.Color(0, 0, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_xoa_2.setText("Xóa");
        btn_xoa_2.setBackground(new java.awt.Color(255, 0, 51));
        btn_xoa_2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_xoa_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_xoa_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa_2ActionPerformed(evt);
            }
        });

        btn_sua_2.setText("Sửa");
        btn_sua_2.setBackground(new java.awt.Color(0, 0, 255));
        btn_sua_2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_sua_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_sua_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua_2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_sua_2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btn_xoa_2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(401, 401, 401)
                    .addComponent(jLabel4)
                    .addContainerGap(746, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton2)
                        .addComponent(jLabel5))
                    .addComponent(jLabel3)
                    .addComponent(jButton1)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(datePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton4))
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton3))
                .addGap(6, 6, 6)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_xoa_2)
                            .addComponent(btn_sua_2))
                        .addGap(29, 29, 29))))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(jLabel4)
                    .addContainerGap(572, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("           Xem theo quãng thời gian           ", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Chọn ngày để xem: ");
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));

        datePicker1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        datePicker1.setForeground(new java.awt.Color(0, 0, 204));

        jPanelNoiDungXemNgay.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Phim", "Phòng chiếu", "Giờ chiếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanelNoiDungXemNgayLayout = new javax.swing.GroupLayout(jPanelNoiDungXemNgay);
        jPanelNoiDungXemNgay.setLayout(jPanelNoiDungXemNgayLayout);
        jPanelNoiDungXemNgayLayout.setHorizontalGroup(
            jPanelNoiDungXemNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1117, Short.MAX_VALUE)
        );
        jPanelNoiDungXemNgayLayout.setVerticalGroup(
            jPanelNoiDungXemNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNoiDungXemNgayLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jLabel2.setText("       ");
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));

        btn_sua_1.setText("Sửa");
        btn_sua_1.setBackground(new java.awt.Color(0, 0, 255));
        btn_sua_1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_sua_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_sua_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua_1ActionPerformed(evt);
            }
        });

        btn_xoa_1.setText("Xóa");
        btn_xoa_1.setBackground(new java.awt.Color(255, 0, 51));
        btn_xoa_1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btn_xoa_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_xoa_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa_1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelNoiDungXemNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_sua_1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btn_xoa_1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelNoiDungXemNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xoa_1)
                    .addComponent(btn_sua_1))
                .addGap(19, 19, 19))
        );

        jTabbedPane2.addTab("           Xem theo ngày           ", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2))
        );

        jTabbedPane1.addTab("           Xem lịch chiếu           ", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Chọn phim:");
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));

        jLabel8.setText("Ngày, Giờ chiếu:");
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));

        jLabel9.setText("Phòng chiếu:");
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        jButton2.setText("Xác nhận");
        jButton2.setBackground(new java.awt.Color(0, 51, 255));
        jButton2.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setText("Chỉ những phòng chưa có suất chiếu tại giờ đã chọn mới được hiển thị");

        jLabel11.setText("Chỉ những phim đang chiếu mới được hiển thị");

        jLabel12.setText("<html>Để tùy chỉnh giờ, chọn giờ gần nhất rồi chỉnh sửa trực tiếp trên ô nhập liệu<br>Bấm enter để xác nhận</html>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(406, 406, 406))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(32, 32, 32)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("           Thêm lịch chiếu mới           ", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setText("Chọn phim:");
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 255));

        jLabel14.setText("Ngày, Giờ chiếu:");
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 255));

        jLabel15.setText("Phòng chiếu:");
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 255));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N

        jButton3.setText("Lưu");
        jButton3.setBackground(new java.awt.Color(0, 51, 255));
        jButton3.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setText("Chỉ những phòng chưa có suất chiếu tại giờ đã chọn mới được hiển thị");

        jLabel17.setText("Chỉ những phim đang chiếu mới được hiển thị");

        jLabel18.setText("<html>Để tùy chỉnh giờ, chọn giờ gần nhất rồi chỉnh sửa trực tiếp trên ô nhập liệu<br>Bấm enter để xác nhận</html>");

        jButton4.setText("Hủy");
        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(36, 36, 36)
                .addComponent(jButton4)
                .addGap(237, 237, 237))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateTimePicker2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(192, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(dateTimePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("           Sửa lịch chiếu           ", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    void updatejPanel4() {
        //update label

        long startTime = jRadioButton1.isSelected() ? Utils.Funcs.datePickerToTimeMilli(datePicker2.getDate()) : 0;
        long endTime = jRadioButton3.isSelected() ? Utils.Funcs.datePickerToTimeMilli(datePicker3.getDate()) : 0;

        String start = startTime == 0 ? "" : "từ " + Utils.Funcs.datePickerToVNeseString(datePicker2.getDate());
        String end = endTime == 0 ? "" : "đến " + Utils.Funcs.datePickerToVNeseString(datePicker3.getDate());
        jLabel6.setText("Đang hiển thị " + start + " " + end);

        try {
            DefaultTableModel model = Utils.Funcs.modelBangLichChieu(((DefaultTableModel) jTable3.getModel()), startTime, endTime);
            jTable3.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        updatejPanel4();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            String movie = jComboBox1.getSelectedItem().toString();
            int movieID = layPhimID(movie);
            String room = jComboBox2.getSelectedItem().toString();
            Long gioBatDauChieu = getTimeMillifromDateTimePicker(dateTimePicker1);
            if (gioBatDauChieu == -1) {
                JOptionPane.showMessageDialog(this, "Chưa đủ dữ liệu ngày / giờ chiếu", "Thông tin sai ", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (Utils.Funcs.themLichChieu(movieID, room, gioBatDauChieu)) {
                JOptionPane.showMessageDialog(this, "Đã thêm lịch chiếu", "Thành công ", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Chưa thêm được thêm lịch chiếu", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Chưa thêm được thêm lịch chiếu\nLỗi: " + ex.toString(), "Thất bại", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    void nhapDuLieuTabSua(String phim, String room, Date time) {
        ComboBoxModel model = jComboBox3.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            Object sel = model.getElementAt(i);
            if (sel.toString().contains(phim)) {
                model.setSelectedItem(sel);
                break;
            }
        }
        LocalDateTime datetime= time.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        dateTimePicker2.datePicker.setDate(datetime.toLocalDate());
        dateTimePicker2.timePicker.setTime(datetime.toLocalTime());
        ComboBoxModel model2 = jComboBox4.getModel();
        for (int i = 0; i < model2.getSize(); i++) {
            Object sel = model2.getElementAt(i);
            if (sel.toString().contains(room)) {
                model2.setSelectedItem(sel);
                break;
            }
        }

    }

    void suaLichChieu(JTable tbl) {
        int[] hangDcChon = tbl.getSelectedRows();
        if (hangDcChon.length <= 0) {
            return;
        } else if (hangDcChon.length > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ chỉnh sửa được 1 hàng", "Nhiều hơn 1 hàng được chọn", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int idDcChon = (int) tbl.getModel().getValueAt(hangDcChon[0], 0);
        jPanel5.setName("           Sửa lịch chiếu (" + idDcChon + ")           ");
        jTabbedPane1.add(jPanel5);
        jTabbedPane1.setSelectedComponent(jPanel5);
        setupTabThemSua(jComboBox3, dateTimePicker2, jComboBox4);
        try {
            nhapDuLieuTabSua(
                    (String) tbl.getModel().getValueAt(hangDcChon[0], 1),
                    (String) tbl.getModel().getValueAt(hangDcChon[0], 2),
                    ((new SimpleDateFormat("yyyy-MM-dd  HH:mm")).parse((String) tbl.getModel().getValueAt(hangDcChon[0], 3))));
        } catch (ParseException ex) {
            Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void xoaLichChieu(JTable tbl) {
        int[] hangDcChon = tbl.getSelectedRows();
        if (hangDcChon.length <= 0) {
            return;
        }

        int ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa những lịch chiếu đã chọn?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) {
            return;
        }

        ArrayList<Integer> idDcChon = new ArrayList<Integer>();
        for (int rowIndex : hangDcChon) {
            int id = (int) tbl.getModel().getValueAt(rowIndex, 0);
            idDcChon.add(id);
        }
        try {
            if (Utils.Funcs.xoaLichChieu(idDcChon)) {
                JOptionPane.showMessageDialog(this, "Đã xóa các lịch chiếu được chọn", "Thành công ", JOptionPane.INFORMATION_MESSAGE);
                if (tbl == jTable1) {
                    updatejPanelNoiDungXemNgay();
                } else if (tbl == jTable3) {
                    updatejPanel4();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa xóa được các lịch chiếu đã chọn\nLý do: Lịch chiếu đã được dùng để đặt vé", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void updateLichChieu(int id) {

        int ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn cập nhật lịch chiếu id: " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) {
            return;
        }
        String movie = jComboBox3.getSelectedItem().toString();
        int movieID = layPhimID(movie);
        String room = jComboBox4.getSelectedItem().toString();
        Long gioBatDauChieu = getTimeMillifromDateTimePicker(dateTimePicker2);
        if (gioBatDauChieu == -1) {
            JOptionPane.showMessageDialog(this, "Chưa đủ dữ liệu ngày / giờ chiếu", "Thông tin sai ", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            if (Utils.Funcs.updateLichChieu(id, movieID, room, gioBatDauChieu)) {
                JOptionPane.showMessageDialog(this, "Đã cập nhật lịch chiếu id: " + id, "Thành công ", JOptionPane.INFORMATION_MESSAGE);
                anTabChinhSua();

                if (jPanelNoiDungXemNgay != null) {
                    updatejPanelNoiDungXemNgay();

                }
                if (jPanel4 != null) {
                    updatejPanel4();

                }

            } else {
                JOptionPane.showMessageDialog(this, "Chưa cập nhật được các lịch chiếu id: " + id, "Thất bại", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(formLichChieu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void anTabChinhSua() {
        jTabbedPane1.remove(jPanel5);
        jTabbedPane1.setSelectedComponent(jPanel1);
    }
    private void btn_xoa_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa_2ActionPerformed
        // TODO add your handling code here:
        xoaLichChieu(jTable3);
    }//GEN-LAST:event_btn_xoa_2ActionPerformed

    private void btn_sua_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua_2ActionPerformed
        // TODO add your handling code here:
        suaLichChieu(jTable3);
    }//GEN-LAST:event_btn_sua_2ActionPerformed

    private void btn_sua_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua_1ActionPerformed
        // TODO add your handling code here:
        suaLichChieu(jTable1);
    }//GEN-LAST:event_btn_sua_1ActionPerformed

    private void btn_xoa_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa_1ActionPerformed
        // TODO add your handling code here:
        xoaLichChieu(jTable1);
    }//GEN-LAST:event_btn_xoa_1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String str = jPanel5.getName();
        int id = Integer.parseInt(str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")")));
        updateLichChieu(id);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        anTabChinhSua();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(formLichChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formLichChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formLichChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formLichChieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formLichChieu().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_sua_1;
    private javax.swing.JButton btn_sua_2;
    private javax.swing.JButton btn_xoa_1;
    private javax.swing.JButton btn_xoa_2;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private com.github.lgooddatepicker.components.DatePicker datePicker2;
    private com.github.lgooddatepicker.components.DatePicker datePicker3;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelNoiDungXemNgay;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
