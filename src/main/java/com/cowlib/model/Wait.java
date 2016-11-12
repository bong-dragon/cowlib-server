package com.cowlib.model;


public class Wait {
    private int id;
    private int callNumberId;
    private int waiterId;
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

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
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

        Wait wait = (Wait) o;

        if (id != wait.id) return false;
        if (callNumberId != wait.callNumberId) return false;
        if (waiterId != wait.waiterId) return false;
        return status != null ? status.equals(wait.status) : wait.status == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + callNumberId;
        result = 31 * result + waiterId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
