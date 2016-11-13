package com.cowlib.model;

public class Borrow {
    private int id;
    private int callNumberId;
    private int borrowerId;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCallNumberId() {
        return callNumberId;
    }

    public void setCallNumberId(int callNumberId) {
        this.callNumberId = callNumberId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Borrow borrow = (Borrow) o;

        if (id != borrow.id) return false;
        if (callNumberId != borrow.callNumberId) return false;
        if (borrowerId != borrow.borrowerId) return false;
        return status != null ? status.equals(borrow.status) : borrow.status == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + callNumberId;
        result = 31 * result + borrowerId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
