package com.cowlib.model;

import lombok.Data;

@Data
public class Reserve {
    private int id;
    private int callNumberId;
    private int reserverId;
    private String status;

}
