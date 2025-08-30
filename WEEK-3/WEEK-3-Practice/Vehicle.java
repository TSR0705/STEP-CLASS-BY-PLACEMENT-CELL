// Base Class
public class Vehicle {
    // ---------- Protected Instance Variables ----------
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    // ---------- Constructor ----------
    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    // ---------- Common Methods ----------
    public void startVehicle() {
        System.out.println(make + " " + model + " started.");
    }

    public void stopVehicle() {
        System.out.println(make + " " + model + " stopped.");
    }

    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled. Current fuel level: " + fuelLevel);
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle Info:");
        System.out.println(year + " " + make + " " + model);
        System.out.println("Fuel Level: " + fuelLevel);
        System.out.println("--------------------------------");
    }

    // ---------- Main Method ----------
    public static void main(String[] args) {
        // Create different types of vehicles
        Vehicle car = new Car("Toyota", "Corolla", 2020, 50, 4);
        Vehicle truck = new Truck("Tata", "LPT", 2018, 80, 10);
        Vehicle motorcycle = new Motorcycle("Honda", "CBR", 2021, 15, true);

        // Demonstrate reuse: All vehicles can use Vehicle’s methods
        car.startVehicle();
        truck.startVehicle();
        motorcycle.startVehicle();

        // Store vehicles in an array (polymorphism)
        Vehicle[] vehicles = {car, truck, motorcycle};

        // Demonstrate polymorphic behavior
        for (Vehicle v : vehicles) {
            v.displayVehicleInfo();  // Each calls its overridden method
            v.refuel(10);            // Common behavior reused
            v.stopVehicle();
        }

        // Comments / Explanation:
        // 1. Reusability: We reused Vehicle’s fields & methods in Car, Truck, Motorcycle
        //    instead of rewriting start/stop/refuel logic for each type.
        // 2. Extensibility: If we add a new vehicle type (e.g., Bus), 
        //    we just extend Vehicle and reuse existing behavior.
        // 3. Benefit: Without inheritance, we'd need to write separate classes 
        //    with duplicate code for start/stop/refuel.
    }
}

// ---------- Derived Classes ----------
class Car extends Vehicle {
    private int doors;

    public Car(String make, String model, int year, double fuelLevel, int doors) {
        super(make, model, year, fuelLevel);
        this.doors = doors;
    }

    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Doors: " + doors);
        System.out.println("--------------------------------");
    }
}

class Truck extends Vehicle {
    private int loadCapacity; // in tons

    public Truck(String make, String model, int year, double fuelLevel, int loadCapacity) {
        super(make, model, year, fuelLevel);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Load Capacity: " + loadCapacity + " tons");
        System.out.println("--------------------------------");
    }
}

class Motorcycle extends Vehicle {
    private boolean hasFairing; // extra covering for aerodynamics

    public Motorcycle(String make, String model, int year, double fuelLevel, boolean hasFairing) {
        super(make, model, year, fuelLevel);
        this.hasFairing = hasFairing;
    }

    @Override
    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println("Has Fairing: " + (hasFairing ? "Yes" : "No"));
        System.out.println("--------------------------------");
    }
}
