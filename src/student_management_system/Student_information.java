/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package student_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI
 */
public class Student_information extends javax.swing.JFrame {
    public static Statement state;
    public static Connection con;
    public static ResultSet rs;
   
    
    public Student_information() {
         
    
        initComponents();
        connect();
        TB();
    }
    public void connect(){
        try {
            String url ="jdbc:mysql://localhost:3306/studentmanagementsystem";
            String user="root";
            String pass="kongming16";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
            
            state=con.createStatement();
            
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    public void TB(){
        try{
        DefaultTableModel tb=new DefaultTableModel();
        tb.addColumn("Information ID");
        tb.addColumn("Student Id");
//        tb.addColumn("Student name");
        tb.addColumn("Gender");
        tb.addColumn("Email");
        tb.addColumn("Address");
        tb.addColumn("Year");
        tb.addColumn("Grade");
        tb.addColumn("Content");
        
        
        jTable2.setModel(tb);
        rs=state.executeQuery("select * from student_information");
        
        while (rs.next()){
            tb.addRow(new Object[]{
                rs.getInt("Student_information_ID"),
                rs.getInt("Student_ID"),
                rs.getInt("Gender_ID"),
                rs.getString("Email"),
                rs.getInt("Address"),
                rs.getInt("Year"),
                rs.getInt("Grade"),
                rs.getInt("ContactNumber"),
                
                
            });
        }
        }
        catch(SQLException e ){
            JOptionPane.showMessageDialog(null,"error"+e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        student_information_btn = new javax.swing.JButton();
        Addstudent_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        search_btn = new javax.swing.JButton();
        attendant = new javax.swing.JButton();
        Manage_course_btn = new javax.swing.JButton();
        home_btn = new javax.swing.JButton();
        update_btn1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlabel_head = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(900, 800));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 573));

        student_information_btn.setBackground(new java.awt.Color(153, 153, 255));
        student_information_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        student_information_btn.setForeground(new java.awt.Color(255, 255, 255));
        student_information_btn.setText("Student Information");
        student_information_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_information_btnActionPerformed(evt);
            }
        });

        Addstudent_btn.setBackground(new java.awt.Color(242, 242, 242));
        Addstudent_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Addstudent_btn.setForeground(new java.awt.Color(153, 153, 255));
        Addstudent_btn.setText("Add Student");
        Addstudent_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Addstudent_btnActionPerformed(evt);
            }
        });

        delete_btn.setBackground(new java.awt.Color(242, 242, 242));
        delete_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        delete_btn.setForeground(new java.awt.Color(153, 153, 255));
        delete_btn.setText("Delete");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        search_btn.setBackground(new java.awt.Color(242, 242, 242));
        search_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        search_btn.setForeground(new java.awt.Color(153, 153, 255));
        search_btn.setText("Search");
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        attendant.setBackground(new java.awt.Color(242, 242, 242));
        attendant.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        attendant.setForeground(new java.awt.Color(153, 153, 255));
        attendant.setText("Attendant");
        attendant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendantActionPerformed(evt);
            }
        });

        Manage_course_btn.setBackground(new java.awt.Color(242, 242, 242));
        Manage_course_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Manage_course_btn.setForeground(new java.awt.Color(153, 153, 255));
        Manage_course_btn.setText("Manage courses");
        Manage_course_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Manage_course_btnActionPerformed(evt);
            }
        });

        home_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        home_btn.setForeground(new java.awt.Color(153, 153, 255));
        home_btn.setText("Home");
        home_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_btnActionPerformed(evt);
            }
        });

        update_btn1.setBackground(new java.awt.Color(242, 242, 242));
        update_btn1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        update_btn1.setForeground(new java.awt.Color(153, 153, 255));
        update_btn1.setText("update");
        update_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(student_information_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(attendant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Manage_course_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Addstudent_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(home_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(home_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(student_information_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(Addstudent_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(update_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(attendant, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(Manage_course_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1100, 911));

        jlabel_head.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlabel_head.setForeground(new java.awt.Color(153, 153, 255));
        jlabel_head.setText("Student Information");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(jlabel_head))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabel_head)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void student_information_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_information_btnActionPerformed
        Student_information nav_Student_information=new Student_information();
        nav_Student_information.show();
        dispose();
    }//GEN-LAST:event_student_information_btnActionPerformed

    private void Addstudent_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Addstudent_btnActionPerformed
        Addstudent nav_Addstudent=new Addstudent();
        nav_Addstudent.show();
        dispose();
    }//GEN-LAST:event_Addstudent_btnActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
       Delete nev_delete=new Delete();
        nev_delete.show();
        dispose();
    }//GEN-LAST:event_delete_btnActionPerformed

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        Search nav_search=new Search();
        nav_search.show();
        dispose();
    }//GEN-LAST:event_search_btnActionPerformed

    private void attendantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendantActionPerformed
        Attendant nav_attendent=new Attendant();
        nav_attendent.show();
        dispose();
    }//GEN-LAST:event_attendantActionPerformed

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

    private void update_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btn1ActionPerformed
        Update nav_update=new Update();
        nav_update.show();
        dispose();
    }//GEN-LAST:event_update_btn1ActionPerformed

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
            java.util.logging.Logger.getLogger(Student_information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student_information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student_information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student_information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student_information().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton Addstudent_btn;
    javax.swing.JButton Manage_course_btn;
    javax.swing.JButton attendant;
    javax.swing.JButton delete_btn;
    javax.swing.JButton home_btn;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    javax.swing.JLabel jlabel_head;
    javax.swing.JButton search_btn;
    javax.swing.JButton student_information_btn;
    javax.swing.JButton update_btn1;
    // End of variables declaration//GEN-END:variables

    private Connection DriverManager(String jdbcmysqllocalhost3306studentmanagementsy) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
