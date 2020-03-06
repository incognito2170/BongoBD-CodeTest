package com.bongotest.designpattern.decorator.models;

import com.bongotest.designpattern.Vehicle;

public class Car implements Vehicle {

    @Override
    public int set_num_of_wheels(){
        return 4;
    }

    @Override
    public int set_num_of_passengers(){
        return 5;
    }

    @Override
    public boolean has_gas(){
        return true;
    }
}
