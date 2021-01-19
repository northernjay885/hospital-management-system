package org.northernjay.hospital_management_system.utils;

import org.northernjay.hospital_management_system.model.Billing;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BillingUtil extends DatabaseUtil {
    public List<Billing> getBillings(String patientId) throws Exception {

        PreparedStatement myStmt = null;
        List<Billing> billings = new ArrayList<>();

        try {
            // get a connection
            myConn = DatabaseConn.getCon();

            // create sql statement
            String sql = "SELECT * FROM billing WHERE patient_id=? ORDER BY id";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, Integer.parseInt(patientId));

            // execute query
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                BigDecimal price = myRs.getBigDecimal("price");
                Timestamp date = myRs.getTimestamp("date");

                // create new billing object
                Billing tempBilling = Billing.builder()
                        .id(id)
                        .name(name)
                        .price(price)
                        .date(date)
                        .build();

                // add it to the list of billings
                billings.add(tempBilling);
            }

            return billings;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public Billing getBilling(String theBillingId) throws Exception {

        Billing theBilling = null;
        int billingId;

        try {
            // convert billing id to int
            billingId = Integer.parseInt(theBillingId);

            // get connection to database
            myConn = DatabaseConn.getCon();

            // create sql to get selected billing
            String sql = "SELECT * FROM billing WHERE id=" + billingId;

            // create prepared statement
            myStmt = myConn.createStatement();

            // execute statement
            myRs = myStmt.executeQuery(sql);

            // retrieve data from result set row
            if (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                BigDecimal price = myRs.getBigDecimal("price");
                Timestamp date = myRs.getTimestamp("date");

                theBilling = Billing.builder()
                        .id(id)
                        .name(name)
                        .price(price)
                        .date(date)
                        .build();
            }
            else {
                throw new Exception("Could not find billing id: " + billingId);
            }

            return theBilling;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void addBilling(Billing theBilling) throws Exception {
        PreparedStatement myStmt = null;
        try {
            // get db connection
            myConn = DatabaseConn.getCon();
            String sql = "INSERT INTO billing (name, patient_id, price, date) "
                    + "VALUES (?, ?, ?, ?)";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theBilling.getName());
            myStmt.setInt(2, theBilling.getPatientId());
            myStmt.setBigDecimal(3, theBilling.getPrice());
            myStmt.setTimestamp(4, theBilling.getDate());

            myStmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(myConn, myStmt, null);
        }
    }

    public void updateBilling(Billing theBilling) throws Exception {

        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = DatabaseConn.getCon();

            // create SQL update statement
            String sql = "UPDATE billing "
                    + "SET name=?, price=?, date=? "
                    + "WHERE id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theBilling.getName());
            myStmt.setBigDecimal(2, theBilling.getPrice());
            myStmt.setTimestamp(3, theBilling.getDate());
            myStmt.setInt(4, theBilling.getId());

            // execute SQL statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public void deleteBilling(String theBillingId) throws Exception {

        PreparedStatement myStmt = null;

        try {
            // convert billing id to int
            int billingId = Integer.parseInt(theBillingId);

            // get connection to database
            myConn = DatabaseConn.getCon();

            // create sql to delete billing
            String sql = "DELETE FROM billing WHERE id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, billingId);

            // execute sql statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }
}
