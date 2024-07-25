/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class Connect {
    public static final String url="jdbc:mysql://localhost:3306/studentmanagementsystem";
    public static final String user="root";
    public static final String password="kongming16";
    public static Connection getConnection(){
        Connection connection=null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(url,user,password);
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
