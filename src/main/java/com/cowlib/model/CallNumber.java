package com.cowlib.model;

import lombok.Data;

@Data
public class CallNumber {
    private int id;
    private int ownerId;
    private int bookMetaId;
    private boolean isDeleted;
}
