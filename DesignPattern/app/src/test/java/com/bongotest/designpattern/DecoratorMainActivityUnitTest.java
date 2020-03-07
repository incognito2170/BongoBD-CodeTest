package com.bongotest.designpattern;

import com.bongotest.designpattern.decorator.activities.DecoratorMainActivity;
import com.bongotest.designpattern.decorator.decorators.BuildDecorator;
import com.bongotest.designpattern.decorator.decorators.FuelDecorator;
import com.bongotest.designpattern.decorator.enums.Build;
import com.bongotest.designpattern.decorator.enums.Fuel;
import com.bongotest.designpattern.decorator.models.Car;
import com.bongotest.designpattern.decorator.models.Plane;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class DecoratorMainActivityUnitTest {

    @Test
    public void testGetVehicleHasGas_testGasUsageForSimpleObject_returnsTrue() {
        Vehicle vehicle = new Car();
        assertTrue(DecoratorMainActivity.getVehicleHasGas(vehicle));
    }

    @Test
    public void testGetVehicleHasGas_testGasUsageForSimpleObject_returnsFalse() {
        Vehicle vehicle = new Plane();
        assertFalse(DecoratorMainActivity.getVehicleHasGas(vehicle));
    }

    @Test
    public void testGetVehicleHasGas_testGasUsageForDecoratedObject_returnsTrue() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Car(), Fuel.CNG), Build.B_2015);
        assertTrue(DecoratorMainActivity.getVehicleHasGas(vehicle));
    }

    @Test
    public void testGetVehicleHasGas_testGasUsageForDecoratedObject_returnsFalse() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Plane(), Fuel.JET_A), Build.B_2019);
        assertFalse(DecoratorMainActivity.getVehicleHasGas(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersSimpleCar_returnsTrue() {
        Vehicle vehicle = new Car();
        assertEquals(5, DecoratorMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersSimpleCar_returnsFalse() {
        Vehicle vehicle = new Car();
        assertNotEquals(6, DecoratorMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersSimplePlane_returnsTrue() {
        Vehicle vehicle = new Plane();
        assertEquals(150, DecoratorMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersSimplePlane_returnsFalse() {
        Vehicle vehicle = new Plane();
        assertNotEquals(350, DecoratorMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersDecoratedCar_returnsTrue() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Car(), Fuel.CNG), Build.B_2015);
        assertEquals(5, DecoratorMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersDecoratedCar_returnsFalse() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Car(), Fuel.CNG), Build.B_2015);
        assertNotEquals(6, DecoratorMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersDecoratedPlane_returnsTrue() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Plane(), Fuel.JET_A), Build.B_2019);
        assertEquals(150, DecoratorMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfPassengers_testNumOfPassengersDecoratedPlane_returnsFalse() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Plane(), Fuel.JET_A), Build.B_2019);
        assertNotEquals(350, DecoratorMainActivity.getVehicleNumOfPassengers(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsSimpleCar_returnsTrue() {
        Vehicle vehicle = new Car();
        assertEquals(4, DecoratorMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsSimpleCar_returnsFalse() {
        Vehicle vehicle = new Car();
        assertNotEquals(3, DecoratorMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsSimplePlane_returnsTrue() {
        Vehicle vehicle = new Plane();
        assertEquals(3, DecoratorMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsSimplePlane_returnsFalse() {
        Vehicle vehicle = new Plane();
        assertNotEquals(4, DecoratorMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsDecoratedCar_returnsTrue() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Car(), Fuel.CNG), Build.B_2015);
        assertEquals(4, DecoratorMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsDecoratedCar_returnsFalse() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Car(), Fuel.CNG), Build.B_2015);
        assertNotEquals(3, DecoratorMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsDecoratedPlane_returnsTrue() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Plane(), Fuel.JET_A), Build.B_2019);
        assertEquals(3, DecoratorMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleNumOfWheels_testNumOfWheelsDecoratedPlane_returnsFalse() {
        Vehicle vehicle = new BuildDecorator(new FuelDecorator(new Plane(), Fuel.JET_A), Build.B_2019);
        assertNotEquals(4, DecoratorMainActivity.getVehicleNumOfWheels(vehicle));
    }

    @Test
    public void testGetVehicleFuel_testDecoratedCarFuel_returnsTrue() {
        Car car = new Car();
        FuelDecorator fuelDecorator = new FuelDecorator(car, Fuel.CNG);
        assertEquals(Fuel.CNG, DecoratorMainActivity.getVehicleFuel(fuelDecorator));
    }

    @Test
    public void testGetVehicleFuel_testDecoratedCarFuel_returnsFalse() {
        Car car = new Car();
        FuelDecorator fuelDecorator = new FuelDecorator(car, Fuel.CNG);
        assertNotEquals(Fuel.JET_A, DecoratorMainActivity.getVehicleFuel(fuelDecorator));
    }

    @Test
    public void testGetVehicleFuel_testDecoratedPlaneFuel_returnsTrue() {
        Plane plane = new Plane();
        FuelDecorator fuelDecorator = new FuelDecorator(plane, Fuel.JET_A);
        assertEquals(Fuel.JET_A, DecoratorMainActivity.getVehicleFuel(fuelDecorator));
    }

    @Test
    public void testGetVehicleFuel_testDecoratedPlaneFuel_returnsFalse() {
        Plane plane = new Plane();
        FuelDecorator fuelDecorator = new FuelDecorator(plane, Fuel.CNG);
        assertNotEquals(Fuel.JET_A, DecoratorMainActivity.getVehicleFuel(fuelDecorator));
    }

    @Test
    public void testGetVehicleBuild_testDecoratedCarBuild_returnsTrue() {
        Car car = new Car();
        BuildDecorator buildDecorator = new BuildDecorator(car, Build.B_2015);
        assertEquals(Build.B_2015, DecoratorMainActivity.getVehicleBuild(buildDecorator));
    }

    @Test
    public void testGetVehicleBuild_testDecoratedCarBuild_returnsFalse() {
        Car car = new Car();
        BuildDecorator buildDecorator = new BuildDecorator(car, Build.B_2015);
        assertNotEquals(Build.B_2019, DecoratorMainActivity.getVehicleBuild(buildDecorator));
    }

    @Test
    public void testGetVehicleBuild_testDecoratedPlaneBuild_returnsTrue() {
        Plane plane = new Plane();
        BuildDecorator buildDecorator = new BuildDecorator(plane, Build.B_2019);
        assertEquals(Build.B_2019, DecoratorMainActivity.getVehicleBuild(buildDecorator));
    }

    @Test
    public void testGetVehicleBuild_testDecoratedPlaneBuild_returnsFalse() {
        Plane plane = new Plane();
        BuildDecorator buildDecorator = new BuildDecorator(plane, Build.B_2019);
        assertNotEquals(Build.B_2015, DecoratorMainActivity.getVehicleBuild(buildDecorator));
    }
}