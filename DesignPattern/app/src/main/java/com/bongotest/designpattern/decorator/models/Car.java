package com.bongotest.designpattern.decorator.models;

public class Car extends BaseVehicle {
    public Car(Car c) {
        super(c);
    }

    @Override
    public int set_num_of_wheels(){
        super.set_num_of_wheels();
        return 4;
    }

    @Override
    public int set_num_of_passengers(){
        super.set_num_of_passengers();
        return 5;
    }

    @Override
    public boolean has_gas(){
        super.has_gas();
        return true;
    }
}
