package org.northernjay.hospital_management_system.controller;

import org.northernjay.hospital_management_system.DatabaseConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthenticationUtil {
    public static Boolean isValid(String username, String password){
        Connection con = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            con = DatabaseConn.getCon();
            String sql = "SELECT * FROM admin WHERE username=" + username;
            myStmt = con.createStatement();
            myRs = myStmt.executeQuery(sql);

            String realPassword = myRs.getString("password");
            if (realPassword.equals(password)) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Database connection error");
        } finally {
            close(con, myStmt, myRs);
        }
        return false;
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}


