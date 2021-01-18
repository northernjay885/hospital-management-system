package org.northernjay.hospital_management_system.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Patient {
    private Integer id;
    private String firstName;
    private String lastName;
    private Boolean inpatient;
}
