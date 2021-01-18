package org.northernjay.hospital_management_system.model;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class Billing {

    private Integer id;
    private String name;
    private Integer patientId;
    private BigDecimal price;
    private Timestamp date;
}
