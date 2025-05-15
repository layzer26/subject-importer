package com.form4.model;

public class Student {

    private String name;
    private int form;

    public Student(String name, int form) {
        this.name = name;
        this.form = form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }
}
