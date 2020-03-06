package com.bongotest.designpattern.decorator.decorators;

import com.bongotest.designpattern.Vehicle;

abstract class VehicleDecorator implements Vehicle {
    Vehicle decoratedVehicle;

    VehicleDecorator(Vehicle decoratedVehicle) {
        super();
        this.decoratedVehicle = decoratedVehicle;
    }
}
