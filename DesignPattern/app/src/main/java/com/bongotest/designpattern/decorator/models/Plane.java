package com.bongotest.designpattern.decorator.models;

import com.bongotest.designpattern.Vehicle;

public class Plane implements Vehicle {

    @Override
    public int set_num_of_wheels(){
        return 3;
    }

    @Override
    public int set_num_of_passengers(){
        return 150;
    }

    @Override
    public boolean has_gas(){
        return false;
    }
}
