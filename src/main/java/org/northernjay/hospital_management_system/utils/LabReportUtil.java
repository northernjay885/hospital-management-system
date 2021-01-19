package org.northernjay.hospital_management_system.utils;

import org.northernjay.hospital_management_system.model.LabReport;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LabReportUtil extends DatabaseUtil {

    public List<LabReport> getLabReports(String patientId) throws Exception {

        PreparedStatement myStmt = null;
        List<LabReport> labReports = new ArrayList<>();

        try {
            // get a connection
            myConn = DatabaseConn.getCon();

            // create sql statement
            String sql = "SELECT * FROM lab_report WHERE patient_id=? ORDER BY id";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, Integer.parseInt(patientId));

            // execute query
            myRs = myStmt.executeQuery();

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                Timestamp date = myRs.getTimestamp("date");

                // create new lab_report object
                LabReport tempLabReport = LabReport.builder()
                        .id(id)
                        .name(name)
                        .date(date)
                        .build();

                labReports.add(tempLabReport);
            }

            return labReports;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public LabReport getLabReport(String theLabReportId) throws Exception {

        LabReport theLabReport = null;
        int labReportId;

        try {
            // convert lab_report id to int
            labReportId = Integer.parseInt(theLabReportId);

            // get connection to database
            myConn = DatabaseConn.getCon();

            // create sql to get selected lab_report
            String sql = "SELECT * FROM lab_report WHERE id=" + labReportId;

            // create prepared statement
            myStmt = myConn.createStatement();

            // execute statement
            myRs = myStmt.executeQuery(sql);

            // retrieve data from result set row
            if (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String name = myRs.getString("name");
                Timestamp date = myRs.getTimestamp("date");

                theLabReport = LabReport.builder()
                        .id(id)
                        .name(name)
                        .date(date)
                        .build();
            }
            else {
                throw new Exception("Could not find lab_report id: " + labReportId);
            }

            return theLabReport;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

    public void addLabReport(LabReport theLabReport) throws Exception {
        PreparedStatement myStmt = null;
        try {
            // get db connection
            myConn = DatabaseConn.getCon();
            String sql = "INSERT INTO lab_report (patient_id, name, date) "
                    + "VALUES (?, ?, ?)";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, theLabReport.getPatientId());
            myStmt.setString(2, theLabReport.getName());
            myStmt.setTimestamp(3, theLabReport.getDate());

            myStmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close(myConn, myStmt, null);
        }
    }

    public void updateLabReport(LabReport theLabReport) throws Exception {

        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = DatabaseConn.getCon();

            // get record if already exists
            String getSql = "SELECT * FROM lab_report WHERE id=" + theLabReport.getId();
            Statement getStmt = myConn.createStatement();
            myRs = getStmt.executeQuery(getSql);

            // create SQL update statement
            String sql = "UPDATE lab_report "
                    + "SET name=?, date=? "
                    + "WHERE id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theLabReport.getName());
            myStmt.setTimestamp(2, theLabReport.getDate());
            myStmt.setInt(3, theLabReport.getId());

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }

    public void deleteLabReport(String theLabReportId) throws Exception {

        PreparedStatement myStmt = null;

        try {
            // convert lab_report id to int
            int labReportId = Integer.parseInt(theLabReportId);

            // get connection to database
            myConn = DatabaseConn.getCon();

            // create sql to delete lab_report
            String sql = "DELETE FROM lab_report WHERE id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, labReportId);

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }
}
