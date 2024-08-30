/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package student_management_system;

import Crud.TeacherEdit;
import java.awt.Font;
import student_management_system.Class.Connect;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author MSI
 */
public final class Teacher extends javax.swing.JFrame {

    /**
     * Creates new form Main_navigation
     */
    public Teacher() {
        
        initComponents();
        teacherDisplay(teacherdisplay);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        student_information_btn = new javax.swing.JButton();
        Manage_course_btn = new javax.swing.JButton();
        home_btn = new javax.swing.JButton();
        student_information_btn1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlabel_head = new javax.swing.JLabel();
        big_container = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        teacherdisplay = new javax.swing.JTable();
        searchtxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        searchbtn = new javax.swing.JButton();
        refreshbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1400, 800));
        setSize(new java.awt.Dimension(900, 800));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 573));

        student_information_btn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        student_information_btn.setForeground(new java.awt.Color(153, 153, 255));
        student_information_btn.setText("Student Information");
        student_information_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_information_btnActionPerformed(evt);
            }
        });

        Manage_course_btn.setBackground(new java.awt.Color(255, 255, 255));
        Manage_course_btn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Manage_course_btn.setForeground(new java.awt.Color(153, 153, 255));
        Manage_course_btn.setText("Manage courses");
        Manage_course_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Manage_course_btnActionPerformed(evt);
            }
        });

        home_btn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        home_btn.setForeground(new java.awt.Color(153, 153, 255));
        home_btn.setText("Home");
        home_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_btnActionPerformed(evt);
            }
        });

        student_information_btn1.setBackground(new java.awt.Color(153, 153, 255));
        student_information_btn1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        student_information_btn1.setForeground(new java.awt.Color(255, 255, 255));
        student_information_btn1.setText("Teacher");
        student_information_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_information_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(student_information_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Manage_course_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(home_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(student_information_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(home_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(student_information_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(student_information_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(Manage_course_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1100, 911));

        jlabel_head.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlabel_head.setForeground(new java.awt.Color(153, 153, 255));
        jlabel_head.setText("Teacher Information");

        big_container.setBackground(new java.awt.Color(255, 255, 255));

        teacherdisplay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        teacherdisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(teacherdisplay);

        javax.swing.GroupLayout big_containerLayout = new javax.swing.GroupLayout(big_container);
        big_container.setLayout(big_containerLayout);
        big_containerLayout.setHorizontalGroup(
            big_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(big_containerLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1060, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        big_containerLayout.setVerticalGroup(
            big_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(big_containerLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("EDIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        searchbtn.setBackground(new java.awt.Color(153, 153, 255));
        searchbtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 255, 255));
        searchbtn.setText("SEARCH");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        refreshbtn.setBackground(new java.awt.Color(153, 153, 255));
        refreshbtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        refreshbtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshbtn.setText("REFRESH");
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(big_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addComponent(jlabel_head)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(searchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jlabel_head)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(big_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void student_information_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_information_btnActionPerformed
        Student_information nav_Student_information=new Student_information();
        nav_Student_information.show();
        dispose();
    }//GEN-LAST:event_student_information_btnActionPerformed

    private void Manage_course_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Manage_course_btnActionPerformed
        Course_management nav_course_management=new Course_management();
        nav_course_management.show();
        dispose();
    }//GEN-LAST:event_Manage_course_btnActionPerformed

    private void home_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnActionPerformed
        Main_dashboard nav_main_dashboard=new Main_dashboard();
        nav_main_dashboard.show();
        dispose();
    }//GEN-LAST:event_home_btnActionPerformed

    private void student_information_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_information_btn1ActionPerformed
        Teacher nav_teacher=new Teacher();
        nav_teacher.show();
        dispose();
    }//GEN-LAST:event_student_information_btn1ActionPerformed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
       String id=searchtxt.getText();
       int Intid=Integer.parseInt(id);
       String searchquery ="Call searchteacher(?)";
       DefaultTableModel tb=new DefaultTableModel();
       teacherdisplay.setModel(tb);
       db(settable(tb,teacherdisplay),searchquery,Intid);
    }//GEN-LAST:event_searchbtnActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        teacherDisplay(teacherdisplay);
        searchtxt.setText("");
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TeacherEdit teacherEdit_nav=new TeacherEdit();
        teacherEdit_nav.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    public static void teacherDisplay(JTable teacherdisplay){
        
        String teacherquery="CALL displayallteacher()";
        DefaultTableModel tb=new DefaultTableModel();
        teacherdisplay.setModel(tb);
        db( settable(tb,teacherdisplay),teacherquery);
        
    }
    public static DefaultTableModel settable(DefaultTableModel tb,JTable teacherdisplay)
    {
        
        JTableHeader header = teacherdisplay.getTableHeader();
        header.setFont(new Font("Verdana", Font.BOLD, 14));
        tb.setColumnIdentifiers(new Object []{
           "ID",
           "Full Name",
           "Subject1",
           "Subject2",
        });
        
        return tb;
    }
    public static void db(DefaultTableModel tb,String teacherquery){
        try(Connection con =Connect.getConnection();
            PreparedStatement prsm=con.prepareStatement(teacherquery)){
            
            try(ResultSet rs =prsm.executeQuery()){
                while(rs.next()){
                    tb.addRow(new Object[]{
                    rs.getInt("idteacher"),
                    rs.getString("fullname"),
                    rs.getString ("subject1_name"),
                    rs.getString("subject2_name")
                    });
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void db(DefaultTableModel tb,String teacherquery,int id ){
        try(Connection con =Connect.getConnection();
            PreparedStatement prsm=con.prepareStatement(teacherquery)){
            prsm.setInt(1,id);
            try(ResultSet rs =prsm.executeQuery()){
                while(rs.next()){
                    tb.addRow(new Object[]{
                    rs.getInt("idteacher"),
                    rs.getString("fullname"),
                    rs.getString ("subject1_name"),
                    rs.getString("subject2_name")
                    });
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Teacher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton Manage_course_btn;
    private javax.swing.JPanel big_container;
    javax.swing.JButton home_btn;
    private javax.swing.JButton jButton1;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    javax.swing.JLabel jlabel_head;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField searchtxt;
    javax.swing.JButton student_information_btn;
    javax.swing.JButton student_information_btn1;
    public static javax.swing.JTable teacherdisplay;
    // End of variables declaration//GEN-END:variables
}