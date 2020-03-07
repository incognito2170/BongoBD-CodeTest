package com.bongotest.designpattern;

import com.bongotest.designpattern.factory.factories.VehicleFactory;
import com.bongotest.designpattern.factory.models.Car;
import com.bongotest.designpattern.factory.models.Plane;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class VehicleFactoryUnitTest {

    @Test
    public void testGetVehicle_testForCarObject_returnsTrue() {
        assertTrue(VehicleFactory.getVehicle("Car") instanceof Car);
    }

    @Test
    public void testGetVehicle_testForCarObjectLowerCase_returnsTrue() {
        assertTrue(VehicleFactory.getVehicle("car") instanceof Car);
    }

    @Test
    public void testGetVehicle_testForCarObject_returnsFalse() {
        assertFalse(VehicleFactory.getVehicle("bar") instanceof Car);
    }

    @Test
    public void testGetVehicle_testForPlaneObject_returnsTrue() {
        assertTrue(VehicleFactory.getVehicle("Plane") instanceof Plane);
    }

    @Test
    public void testGetVehicle_testForPlaneObjectLowerCase_returnsTrue() {
        assertTrue(VehicleFactory.getVehicle("plane") instanceof Plane);
    }

    @Test
    public void testGetVehicle_testForPlaneObject_returnsFalse() {
        assertFalse(VehicleFactory.getVehicle("lane") instanceof Plane);
    }

    @Test
    public void testGetVehicle_testForNullObject_returnsTrue() {
        assertNull(VehicleFactory.getVehicle("bar"));
    }

    @Test
    public void testGetVehicle_testForNullObject_returnsFalse() {
        assertNotNull(VehicleFactory.getVehicle("car"));
    }
}