package com.bongotest.designpattern.factory.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bongotest.designpattern.Vehicle;
import com.bongotest.designpattern.factory.providers.VehicleFactory;

public class FactoryMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Vehicle car = VehicleFactory.createCar(4,6,true);
        printValue(car);

        Vehicle plane = VehicleFactory.createPlane(6,200,false);
        printValue(plane);
    }

    private static void printValue(Vehicle vehicle){
        System.out.println(vehicle.getClass().getSimpleName()+":\n Has gas: "+vehicle.has_gas()+
                "\n Number of Passengers: "+vehicle.set_num_of_passengers()+
                "\n Number of wheels: "+vehicle.set_num_of_wheels()+"\n");
    }
}
