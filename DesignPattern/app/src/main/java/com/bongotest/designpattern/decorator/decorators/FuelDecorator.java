package com.bongotest.designpattern.decorator.decorators;

import android.util.Log;

import com.bongotest.designpattern.Vehicle;
import com.bongotest.designpattern.decorator.enums.Fuel;

public class FuelDecorator extends VehicleDecorator {
    private final String TAG = this.getClass().getSimpleName();
    private Fuel fuel;

    public FuelDecorator(Vehicle decoratedVehicle, Fuel fuel) {
        super(decoratedVehicle);
        this.fuel = fuel;
    }

    @Override
    public int set_num_of_wheels() {
        return decoratedVehicle.set_num_of_wheels();
    }

    @Override
    public int set_num_of_passengers() {
        return decoratedVehicle.set_num_of_passengers();
    }

    @Override
    public boolean has_gas() {
        Log.d(TAG,"The type of fuel used in this vehicle is " + fuel);
        return decoratedVehicle.has_gas();
    }
}
