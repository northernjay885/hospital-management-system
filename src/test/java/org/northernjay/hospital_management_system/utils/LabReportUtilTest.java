package org.northernjay.hospital_management_system.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northernjay.hospital_management_system.model.LabReport;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LabReportUtilTest {

    private final LabReportUtil labReportUtil = new LabReportUtil();
    public static final Integer PATIENT_ID = 2;
    public static final Integer LAB_REPORT_ID = 4;

    @BeforeEach
    void setup() {

    }

    @Test
    void getLabReports() throws Exception {
        List<LabReport> labReports = labReportUtil.getLabReports(String.valueOf(PATIENT_ID));
        assertEquals(labReports.size(), 3);
    }

    @Test
    void getLabReport() throws Exception {
        LabReport labReport = labReportUtil.getLabReport(String.valueOf(LAB_REPORT_ID));
        System.out.println(labReport.toString());
    }

    @Test
    void updateLabReport() throws Exception {
        LabReport labReport = LabReport.builder()
                .id(2)
                .patientId(2)
                .name("Menia")
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        labReportUtil.updateLabReport(labReport);
        LabReport savedReport = labReportUtil.getLabReport(String.valueOf(labReport.getId()));
        assertEquals("Menia", savedReport.getName());
    }

    @Test
    void addLabReport() throws Exception {
        LabReport report = LabReport.builder()
                .id(4)
                .name("AIDS_test")
                .patientId(2)
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        labReportUtil.addLabReport(report);
    }

    @Test
    void deleteLabReport() throws Exception {
        labReportUtil.deleteLabReport(String.valueOf(LAB_REPORT_ID));
    }
}