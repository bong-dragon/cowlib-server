package com.cowlib.model;


public class Wait {
    private int id;
    private String callNumberId;
    private String waiterId;
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

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
