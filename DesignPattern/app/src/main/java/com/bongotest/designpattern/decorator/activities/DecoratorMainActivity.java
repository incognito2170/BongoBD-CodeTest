package com.bongotest.designpattern.decorator.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

        // Creating simple Vehicle objects
        // Creating simple Car object
        Vehicle car = new Car();
        printValue(car);
        // Creating simple Plane object
        Vehicle plane = new Plane();
        printValue(plane);

        // Created Decorated Vehicle objects
        // Creating Decorated Car of B_2015 Build and CNG Fuel
        Vehicle car1 = new BuildDecorator(new FuelDecorator(new Car(), Fuel.CNG), Build.B_2015);
        printValue(car1);
        // Creating Decorated Plane of B_2019 Build and JET_A Fuel
        Vehicle plane1 = new BuildDecorator(new FuelDecorator(new Plane(), Fuel.JET_A), Build.B_2019);
        printValue(plane1);

        // Order of decorator is also not much important here, since all are unique functionalities.
        // We can also do this nesting of functionalities in separate statements.
        // Creating object with similar functionalities in separate statements
        Car car2 = new Car();
        BuildDecorator buildDecorator = new BuildDecorator(car2, Build.B_2015);
        FuelDecorator fuelDecorator = new FuelDecorator(buildDecorator, Fuel.CNG);
        Vehicle car3 = fuelDecorator;
        printValue(car3);
    }

    private static void printValue(Vehicle vehicle){
        System.out.println(vehicle.getClass().getSimpleName()+":\n Has gas: "+vehicle.has_gas()+
                "\n Number of Passengers: "+vehicle.set_num_of_passengers()+
                "\n Number of wheels: "+vehicle.set_num_of_wheels()+"\n");
    }
}
