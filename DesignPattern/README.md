# Source code for Design pattern problem with Local Unit Test code.

* Unit test code is placed under "BongoBD-CodeTest/DesignPattern/app/src/test/java/com/bongotest/designpattern/".

# Q2) Explain the design pattern used in following:

interface Vehicle {
	
	int set_num_of_wheels()
	int set_num_of_passengers()
	boolean has_gas()
}

# 2a) Explain how can you use the pattern to create car and plane class?

# Answer:

The given interface indicates towards the factory deisgn pattern. Here, a interface is given to build two types of objects like Car and Plane. This provides a good platform to use the factory pattern and an opportunity to change the values of the objects on the fly.

To create a car and a plane class, we will simply keep Vehicle class as a base class. The key point of factory design pattern is that we define a class/interface and then we can have subclasses which implement the contract defined by the base class. Here the subclasses are Car and Plane class.

* Car class:

public class Car implements Vehicle {

    private int num_of_wheels,num_of_passengers;
    private boolean has_gas;

    public Car(int num_of_passengers, int num_of_wheels, boolean has_gas){
        this.num_of_passengers = num_of_passengers;
        this.num_of_wheels = num_of_wheels;
        this.has_gas = has_gas;
    }

    @Override
    public int set_num_of_wheels() {
        return num_of_wheels;
    }

    @Override
    public int set_num_of_passengers() {
        return num_of_passengers;
    }

    @Override
    public boolean has_gas() {
        return has_gas;
    }
}

* Plane class:

public class Plane implements Vehicle {

    private int num_of_wheels,num_of_passengers;
    private boolean has_gas;

    public Plane(int num_of_passengers, int num_of_wheels, boolean has_gas){
        this.num_of_passengers = num_of_passengers;
        this.num_of_wheels = num_of_wheels;
        this.has_gas = has_gas;
    }

    @Override
    public int set_num_of_wheels() {
        return num_of_wheels;
    }

    @Override
    public int set_num_of_passengers() {
        return num_of_passengers;
    }

    @Override
    public boolean has_gas() {
        return has_gas;
    }
}

* Factory class:

public class VehicleFactory {

    public static Vehicle createCar (int num_of_wheels, int num_of_passengers,
                                     boolean has_gas){
        return new Car(num_of_passengers, num_of_wheels, has_gas);
    }

    public static Vehicle createPlane ( int num_of_wheels,int num_of_passengers,
                                        boolean has_gas){
        return new Plane(num_of_passengers, num_of_wheels, has_gas);
    }
}

* Main entry point to test the pattern:

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

# 2b) Use a different design pattern for this solution.

# Answer:

As an alternative solution, we can use Decorator design pattern. The reason for choosing this is as follows:

* The decorator design pattern allows us to dynamically add functionality and behavior to an object without affecting the behavior of other existing objects in the same class.
* We use inheritance to extend the behavior of the class. This takes place at compile time, and all of the instances of that class get the extended behavior.
* Decorator design pattern allows us to add functionality to an object (not the class) at runtime, and we can apply this customized functionality to an individual object based on our requirement and choice.
* Decorator patterns allow a user to add new functionality to an existing object without altering its structure. So, there is no change to the original class.
* The decorator design pattern is a structural pattern, which provides a wrapper to the existing class. We can use abstract classes or interfaces with the composition to implement the wrapper.
* Decorator design patterns create decorator classes, which wrap the original class and provide additional functionality by keeping the class methods' signature unchanged.
* Decorator design patterns are most often used for applying single responsibility principles since we divide the functionality into classes with unique areas of concern.

Here, we have the following interface:

interface Vehicle {

	int set_num_of_wheels()
	int set_num_of_passengers()
	boolean has_gas()
}

We can have two concrete classes of Vehicle, namely Car and Plane, to define a specific vehicle.

* Car class:

public class Car implements Vehicle {

    @Override
    public int set_num_of_wheels(){
        return 4;
    }

    @Override
    public int set_num_of_passengers(){
        return 5;
    }

    @Override
    public boolean has_gas(){
        return true;
    }
}

* Plane class:

public class Plane implements Vehicle {

    @Override
    public int set_num_of_wheels(){
        return 3;
    }

    @Override
    public int set_num_of_passengers(){
        return 150;
    }

    @Override
    public boolean has_gas(){
        return false;
    }
}

So far, all is good, and we can create Car and Plane. But, supppose we would like to have some additional functionalities for the Vehicle; like Build, Model, Color, and so on. Decorator pattern will help us to achieve that if we want to do that at any point in future.

Now, let's take a look at the decoration portion. First, we will create an abstract wrapper (decorator) class that implements the Vehicle.

* Decorator class:

public abstract class VehicleDecorator implements Vehicle {

    protected Vehicle decoratedVehicle;
    public VehicleDecorator(Vehicle decoratedVehicle) {
        super();
        this.decoratedVehicle = decoratedVehicle;
    }
}

I kept this abstract to avoid any direct instantiation since this is just a wrapper and does not add any functionality to the vehicle. Also, I have implemented Vehicle to allow adding additional functionalities to all of the defined, concrete Vehicle classes, for example, Car and Plane in this case.

Now, let me show one additional benefit to use decorator pattern with the given Vehicle interface. As mentioned before, we can add extra features to an object without modifying the original class. Lets create enums for Fuel and Build for the vehicles.

* Fuel enum:

public enum Fuel {

    CNG,
    OCTANE,
    ELECTRICITY,
    DIESEL,
    JET_A,
    JET_A1,
    JET_B
}

* Build enum:

public enum Build {

    B_1990,
    B_1992,
    B_1995,
    B_1999,
    B_2002,
    B_2007,
    B_2010,
    B_2015,
    B_2016,
    B_2017,
    B_2018,
    B_2019,
    B_2020
}

Lets create the FuelDecorator to add the functionality of fuel to the vehicle.

* FuelDecorator class:

public class FuelDecorator extends VehicleDecorator {

    private final String TAG = this.getClass().getSimpleName();
    private Fuel fuel;

    public FuelDecorator(Vehicle decoratedVehicle, Fuel fuel) {
        super(decoratedVehicle);
        this.fuel = fuel;
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
        Log.d(TAG,"The type of fuel used in this vehicle is " + fuel);
        return decoratedVehicle.has_gas();
    }
}

Lets create the BuildDecorator to add the functionality of build to the vehicle.

* BuildDecorator class:

public class BuildDecorator extends VehicleDecorator {

    private final String TAG = this.getClass().getSimpleName();
    private Build build;

    public BuildDecorator(Vehicle decoratedVehicle, Build build) {
        super(decoratedVehicle);
        this.build = build;
    }

    @Override
    public int set_num_of_wheels() {
        Log.d(TAG,"The build of the vehicle is " + build);
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
}

Now we can define the main program as entry point to execute and test the decorator code.

* Main entry point to test the pattern:

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
