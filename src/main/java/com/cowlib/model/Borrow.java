package com.cowlib.model;

public class Borrow {
    private int id;
    private String callNumberId;
    private String borrowerId;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCallNumberId() {
        return callNumberId;
    }

    public void setCallNumberId(String callNumberId) {
        this.callNumberId = callNumberId;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
