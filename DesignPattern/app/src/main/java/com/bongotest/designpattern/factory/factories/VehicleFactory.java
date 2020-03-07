package com.bongotest.designpattern.factory.factories;

import com.bongotest.designpattern.Vehicle;
import com.bongotest.designpattern.factory.models.Car;
import com.bongotest.designpattern.factory.models.Plane;

public class VehicleFactory {
    public static Vehicle getVehicle (String vehicle){
        String vehicleName = vehicle.toLowerCase();
        if (Car.class.getSimpleName().toLowerCase().equals(vehicleName)) {
            return new Car();
        } else if (Plane.class.getSimpleName().toLowerCase().equals(vehicleName)) {
            return new Plane();
        }
        return null;
    }
}
