package org.northernjay.hospital_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {

    public final static String DB_PASSWORD = "12345678";
    public final static String DB_USER = "root";
    public static Connection getCon() throws SQLException, ClassNotFoundException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver is loaded successfully");

        //        } catch (Exception e) {
//            System.out.println("JDBC Error!");
//            System.out.println(e.getMessage());
//        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_data", DB_USER, DB_PASSWORD);
    }

}
