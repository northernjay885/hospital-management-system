package org.northernjay.hospital_management_system.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthenticationUtil extends DatabaseUtil {

    public static Boolean isValid(String username, String password){
        Connection con = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            con = DatabaseConn.getCon();
            String sql = String.format("SELECT * FROM admin WHERE username = '%s'", username);
            myStmt = con.createStatement();
            myRs = myStmt.executeQuery(sql);
            if (myRs.next()) {
                String realPassword = myRs.getString("password");
                if (realPassword.equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(con, myStmt, myRs);
        }
        return false;
    }
}


