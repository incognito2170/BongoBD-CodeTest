package com.bongotest.designpattern.decorator.decorators;

import com.bongotest.designpattern.Vehicle;
import com.bongotest.designpattern.decorator.enums.Build;

public class BuildDecorator extends VehicleDecorator {
    private Build build;

    public BuildDecorator(Vehicle decoratedVehicle, Build build) {
        super(decoratedVehicle);
        this.build = build;
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
        return decoratedVehicle.has_gas();
    }

    public Enum<Build> getBuild() {
        return build;
    }
}
