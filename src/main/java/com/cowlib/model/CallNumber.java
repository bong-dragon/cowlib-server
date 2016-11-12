package com.cowlib.model;


public class CallNumber {
    private int id;
    private int ownerId;
    private int bookId;
    private boolean isDeleted = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallNumber that = (CallNumber) o;

        if (id != that.id) return false;
        if (ownerId != that.ownerId) return false;
        if (bookId != that.bookId) return false;
        return isDeleted == that.isDeleted;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ownerId;
        result = 31 * result + bookId;
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
