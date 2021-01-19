package org.northernjay.hospital_management_system.controller;

import org.northernjay.hospital_management_system.model.Patient;
import org.northernjay.hospital_management_system.model.PatientType;
import org.northernjay.hospital_management_system.utils.PatientUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "patient", value = "/patient")
public class PatientController extends HttpServlet {

    PatientUtil patientUtil = new PatientUtil();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");

            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "LIST";
            }

            // route to the appropriate method
            switch (theCommand) {

                case "ADD":
                    addPatient(request, response);
                    break;

                case "LOAD":
                    loadPatient(request, response);
                    break;

                case "UPDATE":
                    updatePatient(request, response);
                    break;

                case "DELETE":
                    deletePatient(request, response);
                    break;

                default:
                    listPatients(request, response);
            }

        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read patient id from form data
        String thePatientId = request.getParameter("patientId");

        // delete patient from database
        patientUtil.deletePatient(thePatientId);

        // send them back to "list patients" page
        listPatients(request, response);
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read patient info from form data
        int id = Integer.parseInt(request.getParameter("patientId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String patientType = request.getParameter("patientType");

        // create a new patient object
        Patient thePatient = Patient.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .patientType(PatientType.valueOf(patientType))
                .build();

        // perform update on database
        patientUtil.updatePatient(thePatient);

        // send them back to the "list patients" page
        listPatients(request, response);

    }

    private void loadPatient(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read patient id from form data
        String thePatientId = request.getParameter("patientId");

        // get patient from database (db util)
        Patient thePatient = patientUtil.getPatient(thePatientId);

        // place patient in the request attribute
        request.setAttribute("THE_PATIENT", thePatient);

        // send to jsp page: update-patient-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-patient-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read patient info from form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String patientType = request.getParameter("patient_type");

        // create a new patient object
        Patient thePatient = Patient.builder()
                .firstName(firstName)
                .lastName(lastName)
                .patientType(PatientType.valueOf(patientType))
                .build();

        // add the patient to the database
        patientUtil.addPatient(thePatient);

        // send back to main page (the patient list)
        listPatients(request, response);
    }

    private void listPatients(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get patients from db util
        List<Patient> patients = patientUtil.getPatients();

        // add patients to the request
        request.setAttribute("PATIENT_LIST", patients);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-patients.jsp");
        dispatcher.forward(request, response);
    }
}
