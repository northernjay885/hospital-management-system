package org.northernjay.hospital_management_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConn {

    private static Connection con;
    public static Connection getCon() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("JDBC Driver is loaded successfully");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3006/hospital_data", "root", "root");

        } catch (Exception e) {
            System.out.println("JDBC Driver not Found!");
        }
        return con;
    }

}
