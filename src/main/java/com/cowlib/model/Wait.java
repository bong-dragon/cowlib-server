package com.cowlib.model;

import lombok.Data;

@Data
public class Wait {
    private int id;
    private int callNumberId;
    private int waiterId;
    private String status;
}
