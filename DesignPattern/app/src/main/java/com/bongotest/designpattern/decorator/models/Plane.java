package com.bongotest.designpattern.decorator.models;

public class Plane extends BaseVehicle {
    public Plane(Plane p) {
        super(p);
    }

    @Override
    public int set_num_of_wheels(){
        super.set_num_of_wheels();
        return 3;
    }

    @Override
    public int set_num_of_passengers(){
        super.set_num_of_passengers();
        return 150;
    }

    @Override
    public boolean has_gas(){
        super.has_gas();
        return false;
    }
}
