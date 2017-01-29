package com.cowlib.jpa.model;

import lombok.Data;

@Data
public class CirculationRecord {

    private User recoder;
    private RecordStatus status;
}
