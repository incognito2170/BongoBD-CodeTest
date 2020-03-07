# Source code for Design pattern problem with Local Unit Test code.

* Unit test code is placed under "BongoBD-CodeTest/DesignPattern/app/src/test/java/com/bongotest/designpattern/".

### Q2) Explain the design pattern used in following:

```java
interface Vehicle {
	int set_num_of_wheels()
	int set_num_of_passengers()
	boolean has_gas()
}
```

### 2a) Explain how can you use the pattern to create car and plane class?

##### Answer:

The given interface indicates towards the **Factory design pattern**. Here, a interface is given to build two types of objects like Car and Plane. This provides a good platform to use the factory pattern and an opportunity to change the values of the objects on the fly.

To create a car and a plane class, we will simply keep Vehicle class as a base class. The key point of factory design pattern is that we define a class/interface and then we can have subclasses which implement the contract defined by the base class. Here the subclasses are Car and Plane class.

* Car class:

```java
public class Car implements Vehicle {

    @Override
    public int set_num_of_wheels() {
        return 4;
    }

    @Override
    public int set_num_of_passengers() {
        return 5;
    }

    @Override
    public boolean has_gas() {
        return true;
    }
}
```

* Plane class:

```java
public class Plane implements Vehicle {

    @Override
    public int set_num_of_wheels() {
        return 6;
    }

    @Override
    public int set_num_of_passengers() {
        return 350;
    }

    @Override
    public boolean has_gas() {
        return false;
    }
}
```

* Factory class:

```java
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
```

* Main entry point to test the pattern:

```java
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
```

**Following are the classes containing the unit testing code for Factory design pattern:**

* FactoryMainActivityUnitTest.java:

```java
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
```

* VehicleFactoryUnitTest.java:

```java
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
```

### 2b) Use a different design pattern for this solution.

##### Answer:

As an alternative solution, we can use **Decorator design pattern**. The reason for choosing this is as follows:

* The decorator design pattern allows us to dynamically add functionality and behavior to an object without affecting the behavior of other existing objects in the same class.
* We use inheritance to extend the behavior of the class. This takes place at compile time, and all of the instances of that class get the extended behavior.
* Decorator design pattern allows us to add functionality to an object (not the class) at runtime, and we can apply this customized functionality to an individual object based on our requirement and choice.
* Decorator patterns allow a user to add new functionality to an existing object without altering its structure. So, there is no change to the original class.
* The decorator design pattern is a structural pattern, which provides a wrapper to the existing class. We can use abstract classes or interfaces with the composition to implement the wrapper.
* Decorator design patterns create decorator classes, which wrap the original class and provide additional functionality by keeping the class methods' signature unchanged.
* Decorator design patterns are most often used for applying single responsibility principles since we divide the functionality into classes with unique areas of concern.

Here, we have the following interface:

```java
interface Vehicle {
	int set_num_of_wheels()
	int set_num_of_passengers()
	boolean has_gas()
}
```

We can have two concrete classes of Vehicle, namely Car and Plane, to define a specific vehicle.

* Car class:

```java
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
```

* Plane class:

```java
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
```

So far, all is good, and we can create Car and Plane. But, supppose we would like to have some additional functionalities for the Vehicle; like Build, Model, Color, and so on. Decorator pattern will help us to achieve that if we want to do that at any point in future.

Now, let's take a look at the decoration portion. First, we will create an abstract wrapper (decorator) class that implements the Vehicle.

* Decorator class:

```java
public abstract class VehicleDecorator implements Vehicle {
    protected Vehicle decoratedVehicle;
    public VehicleDecorator(Vehicle decoratedVehicle) {
        super();
        this.decoratedVehicle = decoratedVehicle;
    }
}
```

I kept this abstract to avoid any direct instantiation since this is just a wrapper and does not add any functionality to the vehicle. Also, I have implemented Vehicle to allow adding additional functionalities to all of the defined, concrete Vehicle classes, for example, Car and Plane in this case.

Now, let me show one additional benefit to use decorator pattern with the given Vehicle interface. As mentioned before, we can add extra features to an object without modifying the original class. Lets create enums for Fuel and Build for the vehicles.

* Fuel enum:

```java
public enum Fuel {
    CNG,
    OCTANE,
    ELECTRICITY,
    DIESEL,
    JET_A,
    JET_A1,
    JET_B
}
```

* Build enum:

```java
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
```

Lets create the FuelDecorator to add the functionality of fuel to the vehicle.

* FuelDecorator class:

```java
public class FuelDecorator extends VehicleDecorator {
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
        return decoratedVehicle.has_gas();
    }

    public Enum<Fuel> getFuel() {
        return fuel;
    }
}
```

Lets create the BuildDecorator to add the functionality of build to the vehicle.

* BuildDecorator class:

```java
public class BuildDecorator extends VehicleDecorator {
    private Build build;

    public BuildDecorator(Vehicle decoratedVehicle, Build build) {
        super(decoratedVehicle);
        this.build = build;
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
        return decoratedVehicle.has_gas();
    }

    public Enum<Build> getBuild() {
        return build;
    }
}
```

Now we can define the main program as entry point to execute and test the decorator code.

* Main entry point to test the pattern:

```java
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
```
As we can see, we have not changed the Core classes Vehicle, Car and Plane. By creating the wrapper and decorator classes, we have added and customized the behavior of Vehicle, Car, and Plane.

**Following is the class containing the unit testing code for Decorator design pattern**

* DecoratorMainActivityUnitTest.java:

```java
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
```
