package com.bongotest.designpattern.factory.models;

import com.bongotest.designpattern.Vehicle;

public class Plane implements Vehicle {
    private int num_of_wheels,num_of_passengers;
    private boolean has_gas;

    public Plane(int num_of_passengers, int num_of_wheels, boolean has_gas){
        this.num_of_passengers = num_of_passengers;
        this.num_of_wheels = num_of_wheels;
        this.has_gas = has_gas;
    }

    @Override
    public int set_num_of_wheels() {
        return num_of_wheels;
    }

    @Override
    public int set_num_of_passengers() {
        return num_of_passengers;
    }

    @Override
    public boolean has_gas() {
        return has_gas;
    }
}
