package org.northernjay.hospital_management_system.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class LabReport {
    private Integer id;
    private Integer patientId;
    private String name;
    private Timestamp date;
}
