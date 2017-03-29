package com.stylight.brands.model;

public class SelectedNames {

    private int id;
    private String name;
    private boolean isChecked;


    public SelectedNames(Integer id, String name, Boolean isChecked) {
        this.id = id;
        this.name = name;
        this.isChecked = isChecked;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        isChecked = isChecked;
    }


}
