/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_management_system.Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class Connect {
    public static final String url="jdbc:mysql://127.0.0.1:3306/studentmanagementsystem";
    public static final String user="root";
    public static final String password="062005hak";
    public static Connection getConnection(){
        Connection connection=null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(url,user,password);
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());

            

            JOptionPane.showMessageDialog(null,e.getMessage(),"Message",JOptionPane.ERROR_MESSAGE);

        }
        return connection;
    }
}
