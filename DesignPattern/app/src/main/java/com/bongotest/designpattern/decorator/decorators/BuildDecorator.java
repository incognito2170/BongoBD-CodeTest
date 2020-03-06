package com.bongotest.designpattern.decorator.decorators;

import android.util.Log;

import com.bongotest.designpattern.Vehicle;
import com.bongotest.designpattern.decorator.enums.Build;

public class BuildDecorator extends VehicleDecorator {
    private final String TAG = this.getClass().getSimpleName();
    private Build build;

    public BuildDecorator(Vehicle decoratedVehicle, Build build) {
        super(decoratedVehicle);
        this.build = build;
    }

    @Override
    public int set_num_of_wheels() {
        Log.d(TAG,"The build of the vehicle is " + build);
        return decoratedVehicle.set_num_of_wheels();
    }

    @Override
    public int set_num_of_passengers() {
        return decoratedVehicle.set_num_of_passengers();
    }

    @Override
    public boolean has_gas() {
        return decoratedVehicle.has_gas();
    }
}
