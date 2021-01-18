package org.northernjay.hospital_management_system.utils;

import org.northernjay.hospital_management_system.model.Patient;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class PatientUtil extends DatabaseUtil {

    public List<Patient> getPatients() throws Exception {

        List<Patient> patients = new ArrayList<>();

        try {
            // get a connection
            myConn = DatabaseConn.getCon();

            // create sql statement
            String sql = "SELECT * FROM patient ORDER BY id";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("firstname");
                String lastName = myRs.getString("lastname");
                Boolean inpatient = myRs.getBoolean("inpatient");

                // create new patient object
                Patient tempPatient = Patient.builder()
                        .id(id)
                        .firstName(firstName)
                        .lastName(lastName)
                        .inpatient(inpatient)
                        .build();

                // add it to the list of patients
                patients.add(tempPatient);
            }

            return patients;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public Patient getPatient(String thePatientId) throws Exception {

        Patient thePatient = null;
        int patientId;

        try {
            // convert patient id to int
            patientId = Integer.parseInt(thePatientId);

            // get connection to database
            myConn = DatabaseConn.getCon();

            // create sql to get selected patient
            String sql = "SELECT * FROM patient WHERE id=" + patientId;

            // create prepared statement
            myStmt = myConn.createStatement();

            // execute statement
            myRs = myStmt.executeQuery(sql);

            // retrieve data from result set row
            if (myRs.next()) {
                int id = myRs.getInt("id");
                String firstName = myRs.getString("firstname");
                String lastName = myRs.getString("lastname");
                Boolean inpatient = myRs.getBoolean("inpatient");
                // use the patientId during construction
                thePatient = Patient.builder()
                        .id(id)
                        .firstName(firstName)
                        .lastName(lastName)
                        .inpatient(inpatient)
                        .build();
            }
            else {
                throw new Exception("Could not find patient id: " + patientId);
            }

            return thePatient;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void updatePatient(Patient thePatient) throws Exception {

        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = DatabaseConn.getCon();

            // create SQL update statement
            String sql = "UPDATE patient "
                    + "SET firstname=?, lastname=?, inpatient=? "
                    + "WHERE id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, thePatient.getFirstName());
            myStmt.setString(2, thePatient.getLastName());
            myStmt.setBoolean(3, thePatient.getInpatient());
            myStmt.setInt(4, thePatient.getId());

            // execute SQL statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public void deletePatient(String thePatientId) throws Exception {

        PreparedStatement myStmt = null;

        try {
            // convert patient id to int
            int patientId = Integer.parseInt(thePatientId);

            // get connection to database
            myConn = DatabaseConn.getCon();

            // create sql to delete patient
            String sql = "DELETE FROM patient WHERE id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, patientId);

            // execute sql statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }
}
