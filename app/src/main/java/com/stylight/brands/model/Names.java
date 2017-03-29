package com.stylight.brands.model;

public class Names {

    private int id;
    private String name;
    private Boolean checked;


    public Names(Integer id, String name, Boolean checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;


    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
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


}
