package org.northernjay.hospital_management_system.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northernjay.hospital_management_system.model.LabReport;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LabReportUtilTest {

    @BeforeEach
    void setup() {
        LabReport report = LabReport.builder()
                .id(4)
                .name("AIDS_test")
                .patientId(2)
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    @Test
    void getLabReports() {
    }

    @Test
    void getLabReport() {
    }

    @Test
    void updateLabReport() {
    }

    @Test
    void deleteLabReport() {
    }
}