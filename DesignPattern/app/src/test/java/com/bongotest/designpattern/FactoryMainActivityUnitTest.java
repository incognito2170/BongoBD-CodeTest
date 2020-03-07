package com.bongotest.designpattern;

import com.bongotest.designpattern.factory.activities.FactoryMainActivity;
import com.bongotest.designpattern.factory.factories.VehicleFactory;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryMainActivityUnitTest {

    @Test
    public void testIsObjectNull_testForNullObject_returnsTrue() {
        Vehicle vehicle = VehicleFactory.getVehicle("Bar");
        assertTrue(FactoryMainActivity.isObjectNull(vehicle));
    }

    @Test
    public void testIsObjectNull_testForNullObject_returnsFalse() {
        Vehicle vehicle = VehicleFactory.getVehicle("Car");
        assertFalse(FactoryMainActivity.isObjectNull(vehicle));
    }

    @Test
    public void testIsObjectNull_testForCaseSensitiveNullObject_returnsFalse() {
        Vehicle vehicle = VehicleFactory.getVehicle("car");
        assertFalse(FactoryMainActivity.isObjectNull(vehicle));
    }

    @Test
    public void testGetVehicleHasGas_testGasUsage_returnsTrue() {
        Vehicle vehicle = VehicleFactory.getVehicle("Car");
        assert vehicle != null;
        assertTrue(FactoryMainActivity.getVehicleHasGas(vehicle));
    }

    @Test
    public void testGetVehicleHasGas_testGasUsage_returnsFalse() {
        Vehicle vehicle = VehicleFactory.getVehicle("Plane");
        assert vehicle != null;
        assertFalse(FactoryMainActivity.getVehicleHasGas(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersCar_returnsTrue() {
        Vehicle vehicle = VehicleFactory.getVehicle("Car");
        assert vehicle != null;
        assertEquals(5, FactoryMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersCar_returnsFalse() {
        Vehicle vehicle = VehicleFactory.getVehicle("Car");
        assert vehicle != null;
        assertNotEquals(6, FactoryMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersPlane_returnsTrue() {
        Vehicle vehicle = VehicleFactory.getVehicle("Plane");
        assert vehicle != null;
        assertEquals(350, FactoryMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersPlane_returnsFalse() {
        Vehicle vehicle = VehicleFactory.getVehicle("Plane");
        assert vehicle != null;
        assertNotEquals(150, FactoryMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsCar_returnsTrue() {
        Vehicle vehicle = VehicleFactory.getVehicle("Car");
        assert vehicle != null;
        assertEquals(4, FactoryMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsCar_returnsFalse() {
        Vehicle vehicle = VehicleFactory.getVehicle("Car");
        assert vehicle != null;
        assertNotEquals(6, FactoryMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsPlane_returnsTrue() {
        Vehicle vehicle = VehicleFactory.getVehicle("Plane");
        assert vehicle != null;
        assertEquals(6, FactoryMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsPlane_returnsFalse() {
        Vehicle vehicle = VehicleFactory.getVehicle("Plane");
        assert vehicle != null;
        assertNotEquals(4, FactoryMainActivity.getVehicleNumOfWheels(vehicle));
    }
}