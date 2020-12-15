package com.fdm.w6.serialization;

public class Director {
    private String first_name;
    private String last_name;

    public Director(){
        super();
    }

    public Director(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    @Override
    public String toString() {
        return String.format("Director: %s %s", first_name, last_name);
    }
}
