    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_management_system.Class;

import Crud.Edit;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import static student_management_system.Student_information.rs;


/**
 *
 * @author MSI
 */

public class Display {

    static public void display(DefaultTableModel tb, Statement state) {
        executeQuery(tb, state, "CALL display();");
    }

    // New function with a different query
    static public void displaysearch(DefaultTableModel tb, Statement state,int id) {
        // Modify the query here
        String newQuery = """
                          SELECT * 
                                \tFROM student_information
                                \t\t  INNER JOIN student 
                                \t\t  ON student_information.Student_ID =student.Student_ID
                                \t\t  INNER JOIN gender 
                                \t\t  ON student_information.Gender_ID=gender.GenderID
                                \t\t  INNER JOIN address
                                \t\t  ON student_information.addressID=address.addressId      
                                          INNER JOIN class
                                          ON student.classID=class.ID
                                          INNER JOIN subject
                                          ON class.subjectid=subject.idsubject
                                          WHERE student.Student_ID="""+id; // Replace this with your new query
        executeQuery(tb, state, newQuery);
        
        
    }

    // Helper method to avoid code duplication
    static private void executeQuery(DefaultTableModel tb, Statement state, String query) {
        tb.setColumnIdentifiers(new Object[]{
            "Student Id",
            "First Name",
            "Last Name",
            "Gender",
            "Birth Date",
            "Email",
            "Address",
            "Class",
            "Subject",
            "Level",
            "Start",
            "End",
            "Content",    
        });

        try (ResultSet rs = state.executeQuery(query)) {
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No data returned from the query.");
            }

            do {
                tb.addRow(new Object[]{
                    rs.getInt("Student_ID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Gender"),
                    rs.getString("birthDate"),
                    rs.getString("Email"),
                    rs.getString("address"),
                    rs.getInt("roomNumber"),
                    rs.getString("subject_name"),
                    rs.getInt("level"),
                    rs.getTime("startTime"),
                    rs.getTime("endTime"),
                    rs.getInt("ContactNumber")
                });
            } while (rs.next());
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
        }
    }
}

