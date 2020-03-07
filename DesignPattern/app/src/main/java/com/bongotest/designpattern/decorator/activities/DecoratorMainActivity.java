package com.bongotest.designpattern.decorator.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bongotest.designpattern.R;
import com.bongotest.designpattern.Vehicle;
import com.bongotest.designpattern.decorator.decorators.BuildDecorator;
import com.bongotest.designpattern.decorator.decorators.FuelDecorator;
import com.bongotest.designpattern.decorator.enums.Build;
import com.bongotest.designpattern.decorator.enums.Fuel;
import com.bongotest.designpattern.decorator.models.Car;
import com.bongotest.designpattern.decorator.models.Plane;

public class DecoratorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_decorator);

        // Creating simple Vehicle objects:
        // Creating simple Car object:
        Vehicle car = new Car();
        printValue(car);
        // Creating simple Plane object:
        Vehicle plane = new Plane();
        printValue(plane);

        // Creating Decorated Vehicle objects:
        // Creating Decorated Car of B_2015 Build and CNG Fuel:
        Vehicle car1 = new BuildDecorator(new FuelDecorator(new Car(), Fuel.CNG), Build.B_2015);
        printValue(car1);
        // Creating Decorated Plane of B_2019 Build and JET_A Fuel:
        Vehicle plane1 = new BuildDecorator(new FuelDecorator(new Plane(), Fuel.JET_A), Build.B_2019);
        printValue(plane1);

        // Order of decorator is also not much important here, since all are unique functionalities.
        // We can also do this nesting of functionalities in separate statements.
        // Creating Decorated Car of B_2015 Build and CNG Fuel in separate statements:
        Car car2 = new Car();
        BuildDecorator buildDecorator1 = new BuildDecorator(car2, Build.B_2015);
        FuelDecorator fuelDecorator1 = new FuelDecorator(buildDecorator1, Fuel.CNG);
        Vehicle car3 = fuelDecorator1;
        printValue(car3, buildDecorator1, fuelDecorator1);
        // Creating Decorated CPlane of B_2019 Build and JET_A Fuel in separate statements:
        Plane plane2 = new Plane();
        BuildDecorator buildDecorator2 = new BuildDecorator(plane2, Build.B_2019);
        FuelDecorator fuelDecorator2 = new FuelDecorator(buildDecorator2, Fuel.JET_A);
        Vehicle plane3 = fuelDecorator2;
        printValue(plane3, buildDecorator2, fuelDecorator2);
    }

    private void printValue(Vehicle vehicle){
        System.out.println(vehicle.getClass().getSimpleName()+":\n Has gas: "+getVehicleHasGas(vehicle)+
                "\n Number of Passengers: "+getVehicleNumOfPassengers(vehicle)+
                "\n Number of wheels: "+getVehicleNumOfWheels(vehicle)+"\n");
    }

    private void printValue(Vehicle vehicle, BuildDecorator buildDecorator, FuelDecorator fuelDecorator){
        System.out.println(vehicle.getClass().getSimpleName()+":\n Has gas: "+getVehicleHasGas(vehicle)+
                "\n Fuel: "+getVehicleFuel(fuelDecorator)+
                "\n Build: "+getVehicleBuild(buildDecorator)+
                "\n Number of Passengers: "+getVehicleNumOfPassengers(vehicle)+
                "\n Number of wheels: "+getVehicleNumOfWheels(vehicle)+"\n");
    }

    public static boolean getVehicleHasGas(Vehicle vehicle) {
        return vehicle.has_gas();
    }

    public static int getVehicleNumOfPassengers(Vehicle vehicle) {
        return vehicle.set_num_of_passengers();
    }

    public static int getVehicleNumOfWheels(Vehicle vehicle) {
        return vehicle.set_num_of_wheels();
    }

    public static Enum<Build> getVehicleBuild(BuildDecorator vehicle) {
        return vehicle.getBuild();
    }

    public static Enum<Fuel> getVehicleFuel(FuelDecorator vehicle) {
        return vehicle.getFuel();
    }
}
