package com.bongotest.designpattern.factory.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bongotest.designpattern.R;
import com.bongotest.designpattern.Vehicle;
import com.bongotest.designpattern.factory.factories.VehicleFactory;

public class FactoryMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_factory);

        Vehicle car = VehicleFactory.getVehicle("Car");
        printValue(car);

        Vehicle plane = VehicleFactory.getVehicle("Plane");
        printValue(plane);
    }

    private void printValue(Vehicle vehicle){
        if (isObjectNull(vehicle)) {
            return;
        }

        System.out.println(vehicle.getClass().getSimpleName()+":\n Has gas: "+getVehicleHasGas(vehicle)+
                "\n Number of Passengers: "+getVehicleNumOfPassengers(vehicle)+
                "\n Number of wheels: "+getVehicleNumOfWheels(vehicle)+"\n");
    }

    public static boolean isObjectNull(Vehicle vehicle) {
        return null == vehicle;
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
}
