package com.bongotest.designpattern.decorator.models;

import com.bongotest.designpattern.Vehicle;

public class BaseVehicle implements Vehicle {

    protected Vehicle vehicle;

    public BaseVehicle(Vehicle c){
        this.vehicle = c;
    }

    @Override
    public int set_num_of_wheels() {
        return this.vehicle.set_num_of_wheels();
    }
    @Override
    public int set_num_of_passengers() {
        return this.vehicle.set_num_of_passengers();
    }

    @Override
    public boolean has_gas() {
        return this.vehicle.has_gas();
    }
}
