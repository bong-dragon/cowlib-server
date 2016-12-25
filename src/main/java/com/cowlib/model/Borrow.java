package com.cowlib.model;

import lombok.Data;

@Data
public class Borrow {
    private int id;
    private int callNumberId;
    private int borrowerId;
    private String status;
}
