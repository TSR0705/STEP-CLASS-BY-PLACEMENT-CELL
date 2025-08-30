import java.time.Year;  // For getting the current year

public class Car {
    // Instance variables (attributes)
    private String brand;
    private String model;
    private int year;
    private String color;
    private boolean isRunning;

    // Constructor to initialize attributes
    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false; // initially engine is off
    }

    // Method to start engine
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(brand + " " + model + " engine started.");
        } else {
            System.out.println(brand + " " + model + " engine is already running.");
        }
    }

    // Method to stop engine
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println(brand + " " + model + " engine stopped.");
        } else {
            System.out.println(brand + " " + model + " engine is already off.");
        }
    }

    // Method to display car information
    public void displayInfo() {
        System.out.println("Car Info:");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Is Running: " + isRunning);
        System.out.println("-------------------------");
    }

    // Method to calculate car age
    public int getAge() {
        int currentYear = Year.now().getValue();
        return currentYear - year;
    }

    public static void main(String[] args) {
        // Creating 3 different Car objects
        Car car1 = new Car("Toyota", "Corolla", 2018, "White");
        Car car2 = new Car("Honda", "Civic", 2020, "Black");
        Car car3 = new Car("Tesla", "Model 3", 2022, "Red");

        // Demonstrating behavior of each car
        car1.displayInfo();
        car1.startEngine();
        System.out.println("Car1 Age: " + car1.getAge() + " years\n");

        car2.displayInfo();
        car2.startEngine();
        car2.stopEngine();
        System.out.println("Car2 Age: " + car2.getAge() + " years\n");

        car3.displayInfo();
        car3.startEngine();
        System.out.println("Car3 Age: " + car3.getAge() + " years\n");

        // Explanation in comments:
        // Each object (car1, car2, car3) has its own state (brand, model, year, color, isRunning).
        // For example, starting car1 does not start car2 or car3.
        // This is similar to real-world cars: Each car is unique and has its own properties & behaviors.
    }
}
