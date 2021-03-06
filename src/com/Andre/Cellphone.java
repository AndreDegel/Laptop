package com.Andre;

/**
 * Created by Andre on 4/20/2015.
 */
public class Cellphone {
    protected String make;
    protected String model;
    protected String staff;
    protected int id;
    protected final int NOID = -1;   //Value to represent no ID known

    Cellphone (String make, String model, String staff) {
        this.make = make;
        this.model = model;
        this.staff = staff;
        this.id = NOID ; //flag for no ID known
    }

    Cellphone (int id, String make, String model, String staff) {
        this(make, model, staff);
        this.id = id;

    }


    protected String getMake() {
        return make;
    }


    protected String getModel() {
        return model;
    }


    protected String getStaff() {
        return staff;
    }

    public String toString() {

        String idData = (this.id == this.NOID) ? "<No ID assigned>" : Integer.toString(this.id) ;

        String phoneData = "Cellphone ID: " +  idData + " Make, Model: " + this.make + " " + this.model + " Assigned to " + this.staff;
        return phoneData;

    }

}
