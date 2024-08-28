/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package student_management_system;

import Crud.Edit_perclass;
import java.awt.Font;
import student_management_system.Class.Connect;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
//import student_management_system.Class.Student_attendant_data;
//import student_management_system.Class.Student_attendant_data;
/**
 *
 * @author MSI
 */
public class Course_management_info extends javax.swing.JFrame {
    public  int classID;
    public String start;
    public String end;
    
    public Course_management_info() {
        initComponents();
        nowdate.setDateToToday();
    }
    
public void settitle(int classID, String start, String end) throws SQLException, ParseException {
    this.classID=classID;
    this.start=start;
    this.end=end;
    DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    // Convert start and end strings to java.sql.Time
    Time startParse = new Time(formatter.parse(start).getTime());
    Time endParse = new Time(formatter.parse(end).getTime());

    String query = "CALL studentmanagementsystem.DisplayTeacher(?,?,?)";
    String query2 = "CALL studentmanagementsystem.display_perclass(?,?,?)";

    // Define table columns including checkboxes
    DefaultTableModel tb = new DefaultTableModel() {
        @Override
        public Class<?> getColumnClass(int column) {
            if (column >= 6 && column <= 8) { // Columns for Present, Absent, Permission
                return Boolean.class;
            }
            return super.getColumnClass(column);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column >= 6 && column <= 8; // Only allow editing of checkbox columns
        }
    };

    JTableHeader header = classtable.getTableHeader();
    header.setFont(new Font("Verdana", Font.BOLD, 14));

    tb.setColumnIdentifiers(new Object[]{
        "ID",
        "First Name",
        "Last Name",
        "Mid term",
        "Final",
        "Total",
        "Present",
        "Excused",
        "Absent",
        "Total "
    });
    classtable.setModel(tb);

    try (Connection con = Connect.getConnection();
         PreparedStatement prsm = con.prepareStatement(query)) {

        // Set parameters for the prepared statement
        prsm.setInt(1, classID);
        prsm.setTime(2, startParse);
        prsm.setTime(3, endParse);

        // Execute the query and handle the result
        try (ResultSet rs = prsm.executeQuery()) {
            if (rs.next()) {
                // Update UI components with results
                teachertxt.setText(rs.getString("fullname"));
                subjecttxt.setText(rs.getString("subject_name"));
                leveltxt.setText(Integer.toString(rs.getInt("level")));
                String startTime = rs.getString("startTime").substring(0, 5);
                String endTime = rs.getString("endTime").substring(0, 5);
                timetxt.setText(startTime + "-" + endTime);
            }
        }

        try (PreparedStatement prsm2 = con.prepareStatement(query2)) {
            prsm2.setInt(1, classID);
            prsm2.setTime(2, startParse);
            prsm2.setTime(3, endParse);

            try (ResultSet rs2 = prsm2.executeQuery()) {
                while (rs2.next()) {
                    tb.addRow(new Object[]{
                        rs2.getInt("Student_ID"),
                        rs2.getString("FirstName"),
                        rs2.getString("LastName"),
                        rs2.getDouble("midterm"),
                        rs2.getDouble("final"),
                        rs2.getDouble("total"),
                        false, // Present checkbox
                        false, // Absent checkbox
                        false, // Permission checkbox
                        rs2.getDouble("total_attendent")
                    });
                }
            }
        }

    } catch (SQLException ex) {
        Logger.getLogger(Course_management_info.class.getName()).log(Level.SEVERE, "SQL Error: ", ex);
    }

    // Add a table model listener to ensure only one checkbox is selected per row
    tb.addTableModelListener(e -> {
        int row = e.getFirstRow();
        int column = e.getColumn();

        // Ensure this listener is triggered by checkbox columns
        if (column >= 6 && column <= 8) {
            boolean isChecked = (Boolean) tb.getValueAt(row, column);

            if (isChecked) {
                for (int i = 6; i <= 8; i++) {
                    if (i != column) {
                        tb.setValueAt(false, row, i);
                    }
                }
            }
        }
    });
}
public void update() {
    DefaultTableModel tb = (DefaultTableModel) classtable.getModel();
    String updateQuery = "CALL studentmanagementsystem.updateRecord(?,?,?)";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed

    try (Connection con = Connect.getConnection();
         PreparedStatement pstmt = con.prepareStatement(updateQuery)) {

        String date = nowdate.getDate().toString(); // Format date as SQL DATE

        for (int row = 0; row < tb.getRowCount(); row++) {
            int studentID = (int) tb.getValueAt(row, 0);
            boolean isPresent = (Boolean) tb.getValueAt(row, 3);
            boolean isPermission = (Boolean) tb.getValueAt(row, 4);
            boolean isAbsent = (Boolean) tb.getValueAt(row, 5);

            String status;
            if (isPresent) {
                status = "Present";
            } else if (isAbsent) {
                status = "Absent";
            } else if (isPermission) {
                status = "Excused";
            } else {
                status = "Not Marked";
            }

            // Print debug information (optional)
            System.out.println(studentID);
            System.out.println(date);
            System.out.println(status);

            // Set parameters for the prepared statement
            pstmt.setInt(1, studentID);
            pstmt.setString(2, status);
            pstmt.setString(3,date); 

            pstmt.executeUpdate();
        }

    } catch (SQLException ex) {
        Logger.getLogger(Course_management_info.class.getName()).log(Level.SEVERE, null, ex);
    }
}
public void tracker() {
    DefaultTableModel tb = (DefaultTableModel) classtable.getModel(); // Get the existing model from classtable
    String date = nowdate.getDate().toString(); // Get the current date as a string
    String insertquery = "CALL insert_attendant(?,?,?)";
    
    try (Connection con = Connect.getConnection(); 
         PreparedStatement pstmt = con.prepareStatement(insertquery)) {
        
        for (int row = 0; row < tb.getRowCount(); row++) {
            int studentID = (int) tb.getValueAt(row, 0);  // Assuming first column is the student ID
            String firstName = (String) tb.getValueAt(row, 1);
            String lastName = (String) tb.getValueAt(row, 2);
            
            boolean isPresent = (Boolean) tb.getValueAt(row, 6);
            boolean isPermission = (Boolean) tb.getValueAt(row, 7);
            boolean isAbsent = (Boolean) tb.getValueAt(row, 8);
            
            String status;
            if (isPresent) {
                status = "Present";
            } else if (isAbsent) {
                status = "Absent";
            } else if (isPermission) {
                status = "Excused";
            } else {
                status = "Not Marked";
            }
            
            // Print to console or store in the database
            System.out.println("Student ID: " + studentID + ", Name: " + firstName + " " + lastName + ", Status: " + status + ", Date: " + date);
            
            // Set parameters for the SQL query
            pstmt.setInt(1, studentID);
            pstmt.setString(2, status);
            pstmt.setString(3, date);
            
            // Execute the update
            pstmt.executeUpdate();
        }
        
        System.out.println("Success");
        
    } catch (SQLException ex) {
        Logger.getLogger(Course_management_info.class.getName()).log(Level.SEVERE, null, ex);
    }
}














public void search() {
    // Assume nowdate is a JDateChooser or similar
    String selectedDate = nowdate.getDate().toString();
    int classid=this.classID;
    String searchquery = "CALL display_record_attendance(?,?,?,?)";
    // Define the format you need for your database (e.g., YYYY-MM-DD)
   
    DefaultTableModel tb = new DefaultTableModel() {
        @Override
        public Class<?> getColumnClass(int column) {
            if (column >= 3 && column <= 5) { // Columns for Present, Excused, Absent
                return Boolean.class;
            }
            return super.getColumnClass(column);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column >= 3 && column <= 5; // Only allow editing of checkbox columns
        }
    };

    JTableHeader header = classtable.getTableHeader();
    header.setFont(new Font("Verdana", Font.BOLD, 14));
        
    tb.setColumnIdentifiers(new Object[]{
        "ID",
        "First Name",
        "Last Name",
        "Present",
        "Excused",
        "Absent",
        "Total"
    });
    classtable.setModel(tb);

    // Assuming start and end are Strings representing time
    
    
    try {
        // Parse start and end time strings into java.sql.Time
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Time startParse = new Time(timeFormat.parse(start).getTime());
        Time endParse = new Time(timeFormat.parse(end).getTime());

        // Database connection and query execution
        try (Connection con = Connect.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(searchquery)) {
//            System.out.println("here");
//            System.out.println(classid);
//            System.out.println(startParse);
//            System.out.println(endParse);
//            System.out.println(selectedDate);
            pstmt.setInt(1, classid);
            pstmt.setTime(2, startParse);
            pstmt.setTime(3, endParse);
            pstmt.setString(4, selectedDate);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    
                    int studentID = rs.getInt("Student_ID");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    double total=rs.getDouble("total_attendent");
                    int status = rs.getInt("Status");

                    // Determine the checkbox states based on the status
                    boolean isPresent = status == 1;
                    boolean isExcused = status == 2;
                    boolean isAbsent = status == 3;
                    System.out.println(firstName);
                    System.out.println(lastName);
                    
                    tb.addRow(new Object[]{
                        studentID,
                        firstName,
                        lastName,
                        isPresent,
                        isExcused,
                        isAbsent,
                        total // Total or any other value if needed
                    });
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course_management_info.class.getName()).log(Level.SEVERE, null, ex);
        }
        tb.addTableModelListener(e -> {
        int row = e.getFirstRow();
        int column = e.getColumn();

        // Ensure this listener is triggered by checkbox columns
        if (column >= 3 && column <= 5) {
            boolean isChecked = (Boolean) tb.getValueAt(row, column);

            if (isChecked) {
                for (int i = 3; i <= 5; i++) {
                    if (i != column) {
                        tb.setValueAt(false, row, i);
                    }
                }
            }
        }
    });
    } catch (ParseException ex) {
        Logger.getLogger(Course_management_info.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("success");
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        timetxt = new javax.swing.JLabel();
        subjecttxt = new javax.swing.JLabel();
        teachertxt = new javax.swing.JLabel();
        leveltxt = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        searchbtn = new javax.swing.JButton();
        resetbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        classtable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        nowdate = new com.github.lgooddatepicker.components.DatePicker();
        update = new javax.swing.JButton();
        submit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Teacher Name:");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 255));

        jLabel2.setText("Subject:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 255));

        jlabel.setText("Time:");
        jlabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlabel.setForeground(new java.awt.Color(153, 153, 255));

        jLabel3.setText("Level:");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jlabel)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jlabel)
                .addGap(25, 25, 25))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        timetxt.setText("Erorr");
        timetxt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        timetxt.setForeground(new java.awt.Color(153, 153, 255));

        subjecttxt.setText("Erorr");
        subjecttxt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        subjecttxt.setForeground(new java.awt.Color(153, 153, 255));

        teachertxt.setText("Erorr");
        teachertxt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        teachertxt.setForeground(new java.awt.Color(153, 153, 255));

        leveltxt.setText("Erorr");
        leveltxt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        leveltxt.setForeground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teachertxt)
                    .addComponent(subjecttxt)
                    .addComponent(timetxt)
                    .addComponent(leveltxt))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(teachertxt)
                .addGap(30, 30, 30)
                .addComponent(subjecttxt)
                .addGap(30, 30, 30)
                .addComponent(leveltxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(timetxt)
                .addGap(25, 25, 25))
        );

        jButton2.setText("EDIT");
        jButton2.setBackground(new java.awt.Color(153, 153, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        searchbtn.setText("Search");
        searchbtn.setBackground(new java.awt.Color(153, 153, 255));
        searchbtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 255, 255));
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        resetbtn.setBackground(new java.awt.Color(153, 153, 255));
        resetbtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        resetbtn.setForeground(new java.awt.Color(255, 255, 255));
        resetbtn.setText("Refresh");
        resetbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(resetbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        classtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "firstName", "lastName", "mid term", "final", "total", "present", "permission", "absion", "total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        classtable.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        classtable.setRowHeight(20);
        jScrollPane1.setViewportView(classtable);

        jButton1.setText("BACK");
        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.setBackground(new java.awt.Color(153, 153, 255));
        update.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        submit.setBackground(new java.awt.Color(153, 153, 255));
        submit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        submit.setForeground(new java.awt.Color(255, 255, 255));
        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nowdate, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 603, Short.MAX_VALUE)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(nowdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Course_management course_management_nav=new Course_management();
        course_management_nav.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Edit_perclass edit_nav=new Edit_perclass(classID,start,end);
        edit_nav.show();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        update();
        try {
            settitle(classID, start, end);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Course_management_info.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        search();
    }//GEN-LAST:event_searchbtnActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        tracker();
        try {
            settitle(classID,start,end);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Course_management_info.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitActionPerformed

    private void resetbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbtnActionPerformed
        try {
            settitle(classID,start,end);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Course_management_info.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_resetbtnActionPerformed
    
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
            java.util.logging.Logger.getLogger(Course_management_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Course_management_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Course_management_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Course_management_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Course_management_info().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable classtable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel leveltxt;
    private com.github.lgooddatepicker.components.DatePicker nowdate;
    private javax.swing.JButton resetbtn;
    private javax.swing.JButton searchbtn;
    private javax.swing.JLabel subjecttxt;
    private javax.swing.JButton submit;
    private javax.swing.JLabel teachertxt;
    private javax.swing.JLabel timetxt;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

}
