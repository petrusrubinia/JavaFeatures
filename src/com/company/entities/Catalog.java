package com.company.entities;

public class Catalog {

    private String studentID;
    private String className;
    private float note;

    public Catalog(String studentID, String className, float note) {
        this.studentID = studentID;
        this.className = className;
        this.note = note;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
