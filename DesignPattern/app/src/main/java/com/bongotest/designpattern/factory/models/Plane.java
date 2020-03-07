package com.bongotest.designpattern.factory.models;

import com.bongotest.designpattern.Vehicle;

public class Plane implements Vehicle {

    @Override
    public int set_num_of_wheels() {
        return 6;
    }

    @Override
    public int set_num_of_passengers() {
        return 350;
    }

    @Override
    public boolean has_gas() {
        return false;
    }
}
